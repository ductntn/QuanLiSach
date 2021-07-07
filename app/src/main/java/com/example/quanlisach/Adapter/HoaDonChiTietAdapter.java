package com.example.quanlisach.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlisach.R;
import com.example.quanlisach.dao.HoaDonChiTietDao;
import com.example.quanlisach.model.HoaDonChiTiet;


import java.util.List;

public class HoaDonChiTietAdapter extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiets;
    private Context context;
    private LayoutInflater inflater;
    HoaDonChiTietDao hoaDonChiTietDao;

    public HoaDonChiTietAdapter(List<HoaDonChiTiet> arrHoaDonChiTiets, Context context) {
        this.arrHoaDonChiTiets = arrHoaDonChiTiets;
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDao = new HoaDonChiTietDao(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiets.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDonChiTiets.get(position);
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
            convertView = inflater.inflate(R.layout.item_hoa_don_chi_tiet,null);
            Holder.tvMaSach=(TextView)convertView.findViewById(R.id.tvMaSach);
            Holder.tvSoLuong=(TextView)convertView.findViewById(R.id.tvSoLuongMua);
            Holder.tvGiaSach=(TextView)convertView.findViewById(R.id.tvGiaSach);
            Holder.tvThanhTien=(TextView)convertView.findViewById(R.id.tvThanhTien);
            Holder.ivIconS=(ImageView)convertView.findViewById(R.id.ivIconHDCT);
            Holder.ivDeleteS=(ImageView)convertView.findViewById(R.id.ivDeleteHD);

            //xu li su kien
            Holder.ivDeleteS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HoaDonChiTiet nd = arrHoaDonChiTiets.get(position);
                    arrHoaDonChiTiets.remove(nd);//xoa trong list
                    notifyDataSetChanged();//cap nhat list
                    hoaDonChiTietDao.deleteHoaDonCT(String.valueOf(nd.getMaHoaDonCT()));
                }
            });

            //tao template
            convertView.setTag(Holder);
        }else {
            Holder = (ViewHolder)convertView.getTag();
        }

        HoaDonChiTiet s = (HoaDonChiTiet) arrHoaDonChiTiets.get(position);
        Holder.tvMaSach.setText("Mã sach:" +s.getSach().getMasach());
        Holder.tvSoLuong.setText("Số lượng sách: "+s.getSoLuongMua()+"");
        Holder.tvGiaSach.setText("Giá sách:" +s.getSach().getGiasach()+"");
        Holder.tvThanhTien.setText("Thành tiền: "+s.getSoLuongMua()*s.getSach().getGiasach()+"");
        return convertView;
    }
    public static class ViewHolder{
        ImageView ivIconS;
        TextView tvMaSach, tvSoLuong, tvGiaSach, tvThanhTien;
        ImageView ivDeleteS;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<HoaDonChiTiet> ls){
        this.arrHoaDonChiTiets = ls;
        notifyDataSetChanged();
    }

}
