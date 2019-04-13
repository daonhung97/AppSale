package com.example.nhung.banhangonline.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.adapter.LoaispAdapter;
import com.example.nhung.banhangonline.adapter.SanphamAdapter;
import com.example.nhung.banhangonline.model.Giohang;
import com.example.nhung.banhangonline.model.Loaisp;
import com.example.nhung.banhangonline.model.Sanpham;
import com.example.nhung.banhangonline.ultil.CheckConnection;
import com.example.nhung.banhangonline.ultil.Server;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewflipper;
    RecyclerView recyclerviewmanhinhchinh;
    NavigationView navigationview;
    ListView listviewmanhinhchinh;
    DrawerLayout drawerlayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id = 0;
    String tenloaisp = "";
    String hinhanhloaisp = "";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang; //trở về màn hình chính để tiếp tục mua hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
            ActionBar();//bắt sự kiên nút ba gạch
            ActionViewFlipper(); //chạy quảng cáo
            GetDuLieuLoaisp();//get điện thoại và laptop vào
            GetDuLieuSPMoiNhat();
            //  Log.d("aaa","connect");
            CatchOnItemListView();
        } else {
            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    } // bắt sự kiện cho menu biểu tượng giỏ hàng

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.nhung.banhangonline.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listviewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            startActivity(intent);
                            intent.putExtra("idsanpham", mangloaisp.get(position).getId());
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LapTopActivity.class);
                            startActivity(intent);
                            intent.putExtra("idsanpham", mangloaisp.get(position).getId());
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkInternet(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        } else {
                            CheckConnection.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });

    }

    private void GetDuLieuSPMoiNhat() {
//        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, Server.Duongdansanphammoinhat, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//               // Log.d("aaa",response);
//                try {
//                    JSONArray arr = new JSONArray(response);
//                    Log.d("aaa", String.valueOf(arr));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //  Log.d("aaa",Server.Duongdansanphammoinhat);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    Log.d("aaa", String.valueOf(response));
                    int ID = 0;
                    String Tensanpham = "";
                    Integer Giasanpham = 0;
                    String Hinhanhsanpham = "";
                    String Motasanpham = "";
                    int IDsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            Tensanpham = jsonObject.getString("tensanpham");
                            Giasanpham = jsonObject.getInt("giasanpham");
                            Hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            Motasanpham = jsonObject.getString("motasanpham");
                            IDsanpham = jsonObject.getInt("idsanpham");
                            mangsanpham.add(new Sanpham(ID, Tensanpham, Giasanpham, Hinhanhsanpham, Motasanpham, IDsanpham));
                            sanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisanpham");
                            hinhanhloaisp = jsonObject.getString("hinhanhsanpham");
                            mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaisp.add(3, new Loaisp(0, "Liên Hệ", "http://phukientrangtrisinhnhat.com/wp-content/uploads/2017/08/Phone-icon.jpg"));
                    mangloaisp.add(4, new Loaisp(0, "Thông tin", "https://www.ief-trisakti.ac.id/images/i1.png"));

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();//khởi tạo
        //cấp phát bộ nhớ
        mangquangcao.add("https://photo-1-baomoi.zadn.vn/w1000_r1/16/12/08/139/21025758/3_97740.jpg");
        mangquangcao.add("https://znews-photo.zadn.vn/w660/Uploaded/JAC2_N3/2014_07_01/Tuan_Hung_muon_lam_vua_quang_cao_1.jpg"); //truyền dl vào
       mangquangcao.add("https://cdn.tgdd.vn/Products/Images/44/184747/acer-aspire-e5-576-34nd-nxgrysv004-600x600.jpg"); //truyền dl vào
        mangquangcao.add("https://znews-photo.zadn.vn/w660/Uploaded/JAC2_N3/2014_07_01/Tuan_Hung_ky_hop_dong_khung_voi_dien_thoai_thuong_hieu_Viet_2.jpg"); //truyền dl vào
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            //load hình ảnh thư viện Picasso
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//căn đều hình
            viewflipper.addView(imageView);
        }
        //bắt sự kiện cho viewflipper tự chạy
        viewflipper.setFlipInterval(5000);//chạy trong 5s
       viewflipper.setAutoStart(true);//tự chạy
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
       Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewflipper.setInAnimation(animation_slide_in);
       viewflipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar); //hàm hỗ trợ toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //gọi lại toolbar
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size); //hình ảnh ba gạch đầu menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { //bắt sự kiện khi click vào
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);//nảy ra giữa

            }
        });
    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewflipper = (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerviewmanhinhchinh = (RecyclerView) findViewById(R.id.recyeclerview);
        navigationview = (NavigationView) findViewById(R.id.navigationview);
        listviewmanhinhchinh = (ListView) findViewById(R.id.listviewmanhinhchinh);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mangloaisp = new ArrayList<>();
        mangloaisp.add(0, new Loaisp(0, "Trang Chính", "https://namsonauto.vn/Style/Style2/img/Home-icon.png"));
        loaispAdapter = new LoaispAdapter(mangloaisp, getApplicationContext());
        listviewmanhinhchinh.setAdapter(loaispAdapter);
        mangsanpham = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerviewmanhinhchinh.setHasFixedSize(true);
        recyclerviewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerviewmanhinhchinh.setAdapter(sanphamAdapter);

        //ktra nếu mảng đã có dữ liệu rồi thì k cần tạo để cấp phát bọ nhớ mới(tránh mất dl)
        if (manggiohang != null) {

        } else {
            manggiohang = new ArrayList<>();
        }
    }
}
