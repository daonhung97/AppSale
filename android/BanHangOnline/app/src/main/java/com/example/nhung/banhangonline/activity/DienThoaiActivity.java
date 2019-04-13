package com.example.nhung.banhangonline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.adapter.DienthoaiAdapter;
import com.example.nhung.banhangonline.model.Sanpham;
import com.example.nhung.banhangonline.ultil.CheckConnection;
import com.example.nhung.banhangonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {

    Toolbar toolbardienthoai;
    ListView listviewdienthoai;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int iddt = 0;
    int page = 1;
    View footerview;
    boolean inLoading = false;
    boolean limitadata = false;
    mHanler mHanler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        Anhxa();
        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
            GetIdloaisp();
            ActionToolbar();//nút trở về trang trước
            GetData(page);//dữ liệu về
            LoadMoreData();
        } else {
            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
            finish();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.nhung.banhangonline.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        listviewdienthoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(i));
                startActivity(intent);
            }
        });
        listviewdienthoai.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView,int FirstItem, int VisibleItem,int TotalItem) {
                if (FirstItem + VisibleItem == TotalItem && TotalItem != 0 && inLoading == false && limitadata == false) {
                    //khi kéo đến vị trí sp sẽ hiện thị
                inLoading = true;
                ThreaData threaData = new ThreaData();
                threaData.start();

                }

            }
        });
    }



    private void ActionToolbar() {
        setSupportActionBar(toolbardienthoai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //tạo nút home

        toolbardienthoai.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { //khi click vào thì chuyển về trang trước
                    finish();

                }
            });

    }

    private void GetIdloaisp() {
        iddt = getIntent().getIntExtra("idsanpham", -1);
        Log.d("giasanpham", iddt + "");
    }

    private void Anhxa() {
        toolbardienthoai = (Toolbar) findViewById(R.id.toolbardienthoai);
        listviewdienthoai = (ListView) findViewById(R.id.listviewdienthoai);
        mangdt = new ArrayList<>();// cấp phát bộ nhớ cho mảng
        dienthoaiAdapter = new DienthoaiAdapter(mangdt,getApplicationContext());
        listviewdienthoai.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHanler = new mHanler();
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = String.format(Server.duongDanSanPham, Page, 1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tendt = "";
                int Giadt = 0;
                String Hinhanhdt = "";
                String Motadt = "";
                int Idspdt = 0;
                if (response != null && response.length() != 2) {
                    listviewdienthoai.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tendt = jsonObject.getString("tensanpham");
                            Giadt = jsonObject.getInt("giasanpham");
                            Hinhanhdt = jsonObject.getString("hinhanhsanpham");
                            Motadt = jsonObject.getString("motasanpham");
                            Idspdt = jsonObject.getInt("idsanpham");
                            mangdt.add(new Sanpham(id,Tendt,Giadt,Hinhanhdt,Motadt,Idspdt));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //xác đinh biến hết dữ liệu
                    limitadata = true;
                    listviewdienthoai.removeFooterView(footerview);
                    CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    } //app cho sản phẩm tự load đến hết và hiển thị thông báo hết sản phẩm
    public class mHanler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listviewdienthoai.addFooterView(footerview);
                    break;
                case 1:
                   GetData(++page);//công page lên xong ms thực hiện function
                    inLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreaData extends Thread{
        @Override
        public void run() {
            mHanler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);//ngủ 3 s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHanler.obtainMessage(1);
            mHanler.sendMessage(message);
            super.run();
        }
    }
}