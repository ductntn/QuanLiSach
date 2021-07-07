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
import android.widget.Toast;

import com.example.quanlisach.R;
import com.example.quanlisach.dao.HoaDonChiTietDao;
import com.example.quanlisach.dao.HoaDonDao;

import com.example.quanlisach.model.HoaDon;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class hoaDonAdapter extends BaseAdapter implements Filterable {
    private Context context;
 List<HoaDon> arrHoaDons;
List<HoaDon> arrSortHoaDons;
    private LayoutInflater inflater;
    private HoaDonDao hoaDonDao;
    private Filter hoaDonFilter;
    private HoaDonChiTietDao hoaDonChiTietDao;
    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

    public hoaDonAdapter(Context context, List<HoaDon> arrHoaDons) {
        super();
        this.context = context;
        this.arrHoaDons = arrHoaDons;
        this.arrSortHoaDons = arrSortHoaDons;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonDao =new HoaDonDao(context);
        hoaDonChiTietDao = new HoaDonChiTietDao(context);

    }

    @Override
    public int getCount() {
        return arrHoaDons.size();
    }

    @Override
    public Object getItem(int position) {
        return arrHoaDons.get(position);
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
            convertView = inflater.inflate(R.layout.item_hoa_don,null);
            Holder.txtMaHoaDon=(TextView)convertView.findViewById(R.id.tvMaHoaDon);
            Holder.txtNgayMua=(TextView)convertView.findViewById(R.id.tvNgayMua);
            Holder.img=(ImageView)convertView.findViewById(R.id.ivIcon);
            Holder.imgDelHD=(ImageView)convertView.findViewById(R.id.ivDeleteHD);

            //xu li su kien
            Holder.imgDelHD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(hoaDonChiTietDao.checkHoaDon(arrHoaDons.get(position).getMaHoaDon())){
                        Toast.makeText(context,"Ban phai xoa du lieu truoc",Toast.LENGTH_LONG).show();
                    }else {
                        HoaDon nd = arrHoaDons.get(position);
                        arrHoaDons.remove(nd);//xoa trong list
                        notifyDataSetChanged();//cap nhat list
                        hoaDonDao.deleteHoaDon(nd.getMaHoaDon());
                    }
                }
            });

            //tao template
            convertView.setTag(Holder);
        }else {
            Holder = (ViewHolder)convertView.getTag();
        }

        HoaDon nd = (HoaDon) arrHoaDons.get(position);
        Holder.txtMaHoaDon.setText(nd.getMaHoaDon());
        Holder.txtNgayMua.setText(sdf.format( nd.getNgayMua()));
        return convertView;

    }


    public static class ViewHolder{
        ImageView img;
        TextView txtMaHoaDon, txtNgayMua;
        ImageView imgDelHD;
    }

    //sua du lieu

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeSataset(List<HoaDon> ls){
        this.arrHoaDons = ls;
        notifyDataSetChanged();
    }
    public void resetData(){
        arrHoaDons=arrSortHoaDons;
    }
    @Override
        public Filter getFilter() {
            if (hoaDonFilter == null){
                hoaDonFilter = new CustomFilter();
            }
            return hoaDonFilter;
        }
        private class CustomFilter extends Filter{
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if(constraint==null || constraint.length()==0){
                        results.values = arrSortHoaDons;
                        results.count = arrSortHoaDons.size();
                }else {
                    List<HoaDon> lsHD = new ArrayList<HoaDon>();
                    for (HoaDon p: arrHoaDons){
                        if(p.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase()))lsHD.add(p);
                    }
                    results.values=lsHD;
                    results.count=lsHD.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count==0){
                    notifyDataSetInvalidated();
                }else {
                    arrHoaDons=(List<HoaDon>)results.values;
                    notifyDataSetChanged();
                }
            }
    }




}
