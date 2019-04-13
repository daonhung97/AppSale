package com.example.nhung.banhangonline.ultil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckConnection {
    public static boolean haveNetworkInternet(Context context) {
        //tạo 2 biến
        boolean haveConnecttedWifi = false;
        boolean haveConnecttedMoblile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if(ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnecttedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnecttedMoblile = true;

        }
        return haveConnecttedWifi || haveConnecttedMoblile;
    }
    public static void  ShowToast_short(Context context,String thongbao){
        Toast.makeText(context,thongbao,Toast.LENGTH_SHORT).show();
        //cấp quyền kiểm tra network
    }
}
