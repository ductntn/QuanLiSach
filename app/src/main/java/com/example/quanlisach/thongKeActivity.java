package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.quanlisach.dao.HoaDonChiTietDao;

public class thongKeActivity extends AppCompatActivity {
TextView tvNgay, tvThang, tvNam;
HoaDonChiTietDao hoaDonChiTietDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        tvNgay = findViewById(R.id.tvThongKeNgay);
        tvThang = findViewById(R.id.tvThongKeThang);
        tvNam = findViewById(R.id.tvThongKeNam);
        hoaDonChiTietDao = new HoaDonChiTietDao(this);
        tvNgay.setText("Hôm nay: "+hoaDonChiTietDao.getDoanhThuTheoNgay()+"VND");
        tvThang.setText("Tháng này: "+hoaDonChiTietDao.getDoanhThuTheoThang()+"VND");
       tvNam.setText("Năm nay: "+hoaDonChiTietDao.getDoanhThuTheoNam()+"VND");
    }
}
