package com.example.quanlisach.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlisach.R;
import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.model.TheLoai;

import java.util.List;


public class theLoaiAdapter extends BaseAdapter {
    private Context context;
    private List<TheLoai> arrTheLoai;
    private LayoutInflater inflater;
    private TheLoaiDao theLoaiDao;

    public theLoaiAdapter(Context context, List<TheLoai> arrTheLoai) {
        this.context = context;
        this.arrTheLoai = arrTheLoai;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        theLoaiDao = new TheLoaiDao(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int position) {
        return arrTheLoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder Holder;
        if(convertView==null){
            //tao view
            Holder=new ViewHolder();
            convertView = inflater.inflate(R.layout.item_the_loai,null);
            Holder.tvTenTL=(TextView)convertView.findViewById(R.id.tvTenTL);
            Holder.tvViTriTL=(TextView)convertView.findViewById(R.id.tvViTriTL);
            Holder.ivIconTL=(ImageView)convertView.findViewById(R.id.ivIconTL);
            Holder.ivDeleteTL=(ImageView)convertView.findViewById(R.id.ivDeleteTL);

            //xu li su kien
            Holder.ivDeleteTL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TheLoai nd = arrTheLoai.get(position);
                    arrTheLoai.remove(nd);//xoa trong list
                    notifyDataSetChanged();//cap nhat list
                    theLoaiDao.deleteTheLoai(nd.getMaTheLoai());
                }
            });

            //tao template
            convertView.setTag(Holder);
        }else {
            Holder = (ViewHolder)convertView.getTag();
        }

        TheLoai tl = (TheLoai) arrTheLoai.get(position);
        Holder.tvTenTL.setText(tl.getTenTheLoai());
        Holder.tvViTriTL.setText(tl.getViTri());
        return convertView;
    }
    public static class ViewHolder{
        ImageView ivIconTL;
        TextView tvTenTL, tvViTriTL;
        ImageView ivDeleteTL;
    }

    //sua du lieu

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<TheLoai> ls){
        this.arrTheLoai=ls;
        notifyDataSetChanged();
    }
}
