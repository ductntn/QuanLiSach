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
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.sach;

import java.util.ArrayList;
import java.util.List;

public class sachAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<sach> arrSach;
    private List<sach> arrSortSach;
    private LayoutInflater inflater;
    private sachDao sachDao;
    private Filter sachFilter;

    public sachAdapter(Context context, List<sach> arrSach) {
        this.context = context;
        this.arrSach = arrSach;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDao = new sachDao(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int position) {
        return arrSach.get(position);
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
            convertView = inflater.inflate(R.layout.item_sach,null);
            Holder.tvTenS=(TextView)convertView.findViewById(R.id.tvTenS);
            Holder.tvGiaS=(TextView)convertView.findViewById(R.id.tvGiaS);
            Holder.ivIconS=(ImageView)convertView.findViewById(R.id.ivIconS);
            Holder.ivDeleteS=(ImageView)convertView.findViewById(R.id.ivDeleteS);

            //xu li su kien
            Holder.ivDeleteS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sach nd = arrSach.get(position);
                    arrSach.remove(nd);//xoa trong list
                    notifyDataSetChanged();//cap nhat list
                    sachDao.deleteSach(nd.getMasach());
                }
            });

            //tao template
            convertView.setTag(Holder);
        }else {
            Holder = (ViewHolder)convertView.getTag();
        }

        sach s = (sach) arrSach.get(position);
        Holder.tvTenS.setText("Ma sach: " + s.getMasach()+" / " + "Ten sach:" +s.getTensach());
        Holder.tvGiaS.setText("Gia sach: "+s.getGiasach()+"");
        return convertView;
    }
    public void resetData1(){
        arrSach=arrSortSach;
    }

    @Override
    public Filter getFilter() {
        if (sachFilter == null){
            sachFilter = new CustomFilter();
        }
        return sachFilter;
    }
    private class CustomFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint==null || constraint.length()==0){
                results.values = arrSortSach;
                results.count=arrSortSach.size();
            }else {
                List<sach> lsSach = new ArrayList<>();
                for (sach p: arrSach){
                    if(p.getMasach().toUpperCase().startsWith(constraint.toString().toUpperCase()))lsSach.add(p);
                }
                results.values=lsSach;
                results.count=lsSach.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
          if (results.count==0){
              notifyDataSetChanged();
          }else {
              arrSach=(List<sach>)results.values;
              notifyDataSetChanged();
          }
        }
    }

    public static class ViewHolder{
        ImageView ivIconS;
        TextView tvTenS, tvGiaS;
        ImageView ivDeleteS;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<sach> ls){
        this.arrSach = ls;
        notifyDataSetChanged();
    }
}
