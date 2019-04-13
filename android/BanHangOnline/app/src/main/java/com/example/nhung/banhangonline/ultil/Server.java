package com.example.nhung.banhangonline.ultil;

import com.android.volley.toolbox.StringRequest;

public class Server {
  //  public static String localhost = "10.16.65.50:8080";
  public static String localhost = "192.168.43.73:8080";
 //   public static String localhost = "192.168.0.10:8080";
 //   public static String localhost = "https://nhunglun097.000webhostapp.com";
    public static String DuongdanLoaisp ="http://" + localhost+"/server/getloaisp.php";
    public static String Duongdansanphammoinhat = "http://" +localhost+"/server/getsanphammoinhat.php";
    public static String duongDanSanPham ="http://" + localhost+"/server/getsanpham.php?page=%d&type=%d";
    public static String Duongdandonhang ="http://" + localhost+"/server/thongtinkhachhang.php?page=";
    public static String Duongdanchitietdonhang ="http://" + localhost+"/server/chitietdonhang.php";
}
