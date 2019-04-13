package com.example.nhung.banhangonline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.adapter.LaptopAdapter;
import com.example.nhung.banhangonline.model.Sanpham;
import com.example.nhung.banhangonline.ultil.CheckConnection;
import com.example.nhung.banhangonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapTopActivity extends AppCompatActivity {
    Toolbar toolbarlaptop;
    ListView listviewlaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglaptop;
    int idlaptop = 0;
    int page = 1;
    View footerview; // gán dl hiện thi thanh 3 gạch
    boolean inLoading = false;
    boolean limitadata = false;
   mHanler mHanler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);

        if (CheckConnection.haveNetworkInternet(getApplicationContext())){
            Anhxa();
            GetIdloaisp();
            ActionToolbar();
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
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
        listviewlaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",manglaptop.get(i));
                startActivity(intent);
            }
        });
        listviewlaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
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
    private void Anhxa() {
        toolbarlaptop = (Toolbar) findViewById(R.id.toolbarlaptop);
        listviewlaptop = (ListView) findViewById(R.id.listviewlaptop);
        manglaptop = new ArrayList<>();// cấp phát bộ nhớ cho mảng
        GetData(page);
        //Toast.makeText(this, "count : "+manglaptop.size(), Toast.LENGTH_SHORT).show();
        laptopAdapter = new LaptopAdapter(manglaptop, getApplicationContext());
        listviewlaptop.setAdapter(laptopAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar,null);
        mHanler = new mHanler();
    }
    private void GetIdloaisp() {
        idlaptop = getIntent().getIntExtra("idsanpham", -1);

    }
    private void ActionToolbar() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //tạo nút home

        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//kết thúc

            }
        });

    }
    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = String.format(Server.duongDanSanPham, Page, 2);
        Log.e("Check", duongdan);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                int id = 0;
                String Tenlaptop = "";
                int Gialaptop = 0;
                String Hinhanhlaptop = "";
                String Motalaptop = "";
                int Idsplaptop = 0;
                //Điều kiện khác rỗng và khác 2
                if (response != null && response.length() != 2) {

                   listviewlaptop.removeFooterView(footerview);//tắt dl
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Toast.makeText(LapTopActivity.this, "idsp: "+id, Toast.LENGTH_SHORT).show();
                            Tenlaptop = jsonObject.getString("tensanpham");
                            Gialaptop = jsonObject.getInt("giasanpham");
                            Hinhanhlaptop = jsonObject.getString("hinhanhsanpham");
                            Motalaptop = jsonObject.getString("motasanpham");
                            Idsplaptop = jsonObject.getInt("idsanpham");
                            manglaptop.add(new Sanpham(id, Tenlaptop, Gialaptop, Hinhanhlaptop, Motalaptop, Idsplaptop));
                            Toast.makeText(LapTopActivity.this, "hhhhh: "+manglaptop.size(), Toast.LENGTH_SHORT).show();
                            laptopAdapter.notifyDataSetChanged();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    //xác đinh biến hết dữ liệu
                    limitadata = true;
                    listviewlaptop.removeFooterView(footerview);
                    CheckConnection.ShowToast_short(getApplicationContext(),"Bạn đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idsanpham", String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    } //app cho sản phẩm tự load đến hết và hiển thị thông báo hết sản phẩm

    // Phân bổ từng công vc
    public class mHanler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listviewlaptop.addFooterView(footerview);
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
