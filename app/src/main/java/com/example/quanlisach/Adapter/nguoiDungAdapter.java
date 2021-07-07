package com.example.quanlisach.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlisach.R;
import com.example.quanlisach.dao.NguoiDungDao;
import com.example.quanlisach.model.NguoiDung;

import java.util.List;

public class nguoiDungAdapter extends BaseAdapter {
    private Context context;
    private List<NguoiDung> arrNguoiDung;
    private LayoutInflater inflater;
    private NguoiDungDao nguoiDungDao;

    public nguoiDungAdapter(Context context, List<NguoiDung> arrNguoiDung) {
        this.context = context;
        this.arrNguoiDung = arrNguoiDung;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao=new NguoiDungDao(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
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
            convertView = inflater.inflate(R.layout.item_nguoi_dung,null);
            Holder.txtName=(TextView)convertView.findViewById(R.id.tvName);
            Holder.txtPhone=(TextView)convertView.findViewById(R.id.tvPhone);
            Holder.img=(ImageView)convertView.findViewById(R.id.ivIcon);
            Holder.imgDel=(ImageView)convertView.findViewById(R.id.ivDelete);

            //xu li su kien
            Holder.imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  NguoiDung nd = arrNguoiDung.get(position);
                  arrNguoiDung.remove(nd);//xoa trong list
                    notifyDataSetChanged();//cap nhat list
                    nguoiDungDao.deleteNguoiiDung(nd.getUsername());
                }
            });

            //tao template
            convertView.setTag(Holder);
        }else {
            Holder = (ViewHolder)convertView.getTag();
        }

        NguoiDung nd = (NguoiDung)arrNguoiDung.get(position);
        Holder.txtName.setText(nd.getHoten());
        Holder.txtPhone.setText(nd.getPhone());
        return convertView;
    }
    public static class ViewHolder{
        ImageView img;
        TextView txtName, txtPhone;
        ImageView imgDel;
    }

    //sua du lieu

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeSataset(List<NguoiDung> ls){
        this.arrNguoiDung = ls;
        notifyDataSetChanged();
    }
}
