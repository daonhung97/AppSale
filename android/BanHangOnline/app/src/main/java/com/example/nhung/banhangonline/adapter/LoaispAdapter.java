package com.example.nhung.banhangonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    ArrayList<Loaisp> arrayListloaisp;
    Context context; // truyền vào màn hình

    public LoaispAdapter(ArrayList<Loaisp> arrayListloaisp, Context context) {
        this.arrayListloaisp = arrayListloaisp;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHoder{
            TextView txttenloaisp;
            ImageView imgloaisp;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if (view == null ){
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHoder.txttenloaisp = view.findViewById(R.id.textviewloaisp);
            viewHoder.imgloaisp = view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) view.getTag();

        }
        Loaisp loaisp = (Loaisp) getItem(position);
        viewHoder.txttenloaisp.setText(loaisp.getTenloaisanpham());
        Picasso.with(context).load(loaisp.getHinhanhloaisanpham())
                .placeholder(R.drawable.imges1)
                .error(R.drawable.error)
                .into(viewHoder.imgloaisp);
        return view;
    }
}
