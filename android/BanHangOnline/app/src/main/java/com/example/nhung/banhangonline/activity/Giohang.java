package com.example.nhung.banhangonline.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.adapter.GiohangAdapter;
import com.example.nhung.banhangonline.ultil.CheckConnection;

import java.text.DecimalFormat;

public class Giohang extends AppCompatActivity {

    ListView lvgiohang;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan,btntieptucmuahang;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CactchOnItemListView(); // bắt sự kiện vc xóa sp
        EventButton(); // bắt sự kiện tiếp tục mua hàng
    }

    private void EventButton() {
        btntieptucmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);

                }else {
                    CheckConnection.ShowToast_short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm để thanh toán");

                }
            }
        });
    }

    private void CactchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Giohang.this);
               builder.setTitle("Xác nhận xóa sản phẩm ");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                   if (MainActivity.manggiohang.size() <= 0 ){
                       txtthongbao.setVisibility(View.VISIBLE);
                   }  else {
                       MainActivity.manggiohang.remove(position);
                       giohangAdapter.notifyDataSetChanged(); // cập nhật lại giỏ hàng
                       EvenUltil();//cập nhật lại listview
                       if (MainActivity.manggiohang.size()  <= 0){
                           txtthongbao.setVisibility(View.VISIBLE);
                       }else {
                           txtthongbao.setVisibility(View.INVISIBLE);
                           giohangAdapter.notifyDataSetChanged();
                           EvenUltil();
                       }
                   }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged(); // cập nhật lại giỏ hàng
                        EvenUltil(); //cập nhật lại listview
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EvenUltil() {
        long tongtien=0;
        for (int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) + "Đ");

    }

    private void CheckData() {
        if (MainActivity.manggiohang.size() <=0) {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);

        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang =(ListView) findViewById(R.id.listviewgiohang);
        txtthongbao =(TextView) findViewById(R.id.textviewthongbao);
        txttongtien = (TextView)findViewById(R.id.textviewtongtien);
        btnthanhtoan =(Button) findViewById(R.id.buttonthanhtoangiohang);
        btntieptucmuahang =(Button) findViewById(R.id.buttontieptucmuahang);
        toolbargiohang =(Toolbar) findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(Giohang.this,MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
