package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlisach.Adapter.HoaDonChiTietAdapter;
import com.example.quanlisach.dao.HoaDonChiTietDao;
import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.HoaDon;
import com.example.quanlisach.model.HoaDonChiTiet;
import com.example.quanlisach.model.sach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class addHoaDonChiTiet extends AppCompatActivity {
EditText mhd1,sl1,ms1;
TextView tvthanhtien;
ListView lscart;
HoaDonChiTietDao hoaDonChiTietDao;
sachDao sachDao;
List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
 ;
HoaDonChiTietAdapter hoaDonChiTietAdapter=null;
int thanhtien=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don_chi_tiet);
        mhd1 =findViewById(R.id.edMaHoaDon1);
        ms1 =findViewById(R.id.edMaSach1);
        sl1 =findViewById(R.id.edSoLuongMua1);
        lscart=findViewById(R.id.lvCart);
        tvthanhtien=findViewById(R.id.tvThanhTien);

       hoaDonChiTietAdapter= new HoaDonChiTietAdapter(dsHDCT,this);
       lscart.setAdapter(hoaDonChiTietAdapter);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            mhd1.setText(bundle.getString("MAHOADON"));
        }
//        Intent intent = getIntent();
//       String mhd= intent.getStringExtra("maHoaDon");
//       String nm = intent.getStringExtra("ngayMua");
//       mhd1.setText(mhd);
//       getCategory();
    }

    public void ADDHoaDonCHITIET(View view) {
     hoaDonChiTietDao =new HoaDonChiTietDao(this);
     sachDao= new sachDao(this);
     try {
         if (validation()<0){
             Toast.makeText(getApplicationContext(),"nhap day du thong tin",Toast.LENGTH_LONG).show();
         }else {
             sach sach = sachDao.getSachbyID(ms1.getText().toString());
             if (sach!=null){
                 int pos= checkMaSach(dsHDCT,ms1.getText().toString());
                 HoaDon hoaDon=new HoaDon(mhd1.getText().toString(),new Date());
                 HoaDonChiTiet hoaDonChiTiet= new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(sl1.getText().toString()));
                 if (pos>=0){
                     int soLuong = dsHDCT.get(pos).getSoLuongMua();
                     hoaDonChiTiet.setSoLuongMua(soLuong+Integer.parseInt(sl1.getText().toString()));
                     dsHDCT.set(pos,hoaDonChiTiet);
                 }else {
                     dsHDCT.add(hoaDonChiTiet);
                 }
                 hoaDonChiTietAdapter.changeDataset(dsHDCT);
             }else {
                 Toast.makeText(getApplicationContext(),"Ma sach ko ton tai",Toast.LENGTH_LONG).show();
             }
         }
     }catch (Exception e){

     }

    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDao = new HoaDonChiTietDao(addHoaDonChiTiet.this);

        try {
            for (HoaDonChiTiet hd : dsHDCT){
                hoaDonChiTietDao.insertHoaDonChiTiet(hd);
                thanhtien=thanhtien+hd.getSoLuongMua()*hd.getSach().getGiasach();
            }
            tvthanhtien.setText("Tong tien: "+thanhtien);
            Toast.makeText(getApplicationContext(),"Ma hoa don:" +mhd1.getText().toString()+ ". Thanh tien: "+thanhtien,Toast.LENGTH_LONG).show();
            Intent intent =new Intent(addHoaDonChiTiet.this,hoaDonActivity.class);
            startActivity(intent);
        }catch (Exception e){

        }
    }
  public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach){
        int pos=-1;
        for (int i=0 ;i<lsHD.size();i++){
            HoaDonChiTiet hs = lsHD.get(i);
            if (hs.getSach().getMasach().equalsIgnoreCase(maSach)){
                pos=i;
                break;
            }
        }
        return pos;
  }
    public  int validation(){
        if(ms1.getText().toString().isEmpty()||mhd1.getText().toString().isEmpty()||sl1.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
