package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.quanlisach.Adapter.HoaDonChiTietAdapter;
import com.example.quanlisach.dao.HoaDonChiTietDao;
import com.example.quanlisach.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class hoaDonChiTietAcitivity extends AppCompatActivity {
public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
ListView lvCart;
HoaDonChiTietAdapter hoaDonChiTietAdapter=null;
HoaDonChiTietDao hoaDonChiTietDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet_acitivity);
        lvCart=findViewById(R.id.lvHoaDonChiTiet1);
        hoaDonChiTietDao= new HoaDonChiTietDao(hoaDonChiTietAcitivity.this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            dsHDCT=hoaDonChiTietDao.getAllHoaDonChiTietByID(bundle.getString("MAHOADON"));
        }
        hoaDonChiTietAdapter= new HoaDonChiTietAdapter(dsHDCT,this);
        lvCart.setAdapter(hoaDonChiTietAdapter);
    }
}
