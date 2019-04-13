package com.example.nhung.banhangonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhung.banhangonline.R;
import com.example.nhung.banhangonline.model.Loaisp;
import com.example.nhung.banhangonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DienthoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arraydienthoai;

    public DienthoaiAdapter(ArrayList<Sanpham> arraydienthoai, Context context) {
        this.arraydienthoai = arraydienthoai;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arraydienthoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arraydienthoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHorder {
        public TextView txttendienthoai, txtgiadienthoai, txtmotadienthoai;
        public ImageView imgdienthoai;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHorder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHorder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_dienthoai, null);
            viewHolder.txttendienthoai = (TextView) view.findViewById(R.id.textviewtendienthoai);
            viewHolder.txtgiadienthoai = (TextView) view.findViewById(R.id.textviewgiadienthoai);
            viewHolder.txtmotadienthoai = (TextView) view.findViewById(R.id.textviewmotadienthoai);
            viewHolder.imgdienthoai = (ImageView) view.findViewById(R.id.imageviewdienthoai);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHorder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttendienthoai.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiadienthoai.setText("Giá:" + decimalFormat.format(sanpham.getGiasanpham()) + "Đ");
        viewHolder.txtmotadienthoai.setMaxLines(2);// cho chữ hiện 2 dòng
        viewHolder.txtmotadienthoai.setEllipsize(TextUtils.TruncateAt.END);//dấu 3 chấm ở sau
        viewHolder.txtmotadienthoai.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.imges1)
                .error(R.drawable.error)
                .into(viewHolder.imgdienthoai);
        return view;
    }
}
