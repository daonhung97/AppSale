package com.example.nhung.banhangonline.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraylaptop;

    public LaptopAdapter(ArrayList<Sanpham> arraylaptop, Context context) {
        this.arraylaptop = arraylaptop;
        this.context = context;
    }
    @Override
    public int getCount() {
        return arraylaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHorder{
        public TextView txttenlaptop,txtgialaptop,txtmotalaptop;
        public ImageView imglaptop;

    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LaptopAdapter.ViewHorder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHorder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txttenlaptop =(TextView) view.findViewById(R.id.textviewtenLaptop);
            viewHolder.txtgialaptop =(TextView) view.findViewById(R.id.textviewgiaLaptop);
            viewHolder.txtmotalaptop =(TextView) view.findViewById(R.id.textviewmotaLaptop);
            viewHolder.imglaptop =(ImageView) view.findViewById(R.id.imageviewLaptop);
            view.setTag(viewHolder);

        }else {
            viewHolder= (ViewHorder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(position);
        viewHolder.txttenlaptop.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgialaptop.setText("Giá:"+decimalFormat.format(sanpham.getGiasanpham())+"Đ");
        viewHolder.txtmotalaptop.setMaxLines(2);// cho chữ hiện 2 dòng(số dòng)
        viewHolder.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);//dấu 3 chấm ở sau
        viewHolder.txtmotalaptop.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imges1)
                .error(R.drawable.error)
                .into(viewHolder.imglaptop);
        return view;
    }
}
