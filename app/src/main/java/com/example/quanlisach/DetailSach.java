package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.TheLoai;
import com.example.quanlisach.model.sach;

import java.util.ArrayList;
import java.util.List;

public class DetailSach extends AppCompatActivity {
    List<TheLoai> theLoaiList = new ArrayList<>();
Spinner spCNS;
EditText CNten, CNTacGia, CNSoLuong, CNGiaSach, CNNXB;
String ten,tacgia,soluong,giasach,nxb,masach,maTheLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sach);
        CNten = findViewById(R.id.CNtenSach);
        CNTacGia = findViewById(R.id.CNtacGia);
        CNSoLuong = findViewById(R.id.CNsoLuong);
        CNGiaSach = findViewById(R.id.CNgiasach);
        CNNXB = findViewById(R.id.CNnhaXuatBan);

        spCNS = findViewById(R.id.spCNS);
        getCategory();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        ten = bundle.getString("tensach");
        masach = bundle.getString("masach");
        maTheLoai = bundle.getString("matheloai");
        tacgia = bundle.getString("tacgia");
        soluong = bundle.getString("soluong");
        giasach = bundle.getString("giasach");
        nxb = bundle.getString("nxb");

        CNten.setText(ten);
        CNTacGia.setText(tacgia);
        CNSoLuong.setText(soluong);
        CNGiaSach.setText(giasach);
        CNNXB.setText(nxb);



    }

    public void CNSach(View view) {
        sach sach = new sach();
        sach.setMasach(masach);
        sach.setMaTheLoai(spCNS.getSelectedItem().toString());
        sach.setTensach(CNten.getText().toString());
        sach.setTacgia(CNTacGia.getText().toString());
        sach.setSoluong(Integer.parseInt(CNSoLuong.getText().toString()));
        sach.setGiasach(Integer.parseInt(CNGiaSach.getText().toString()));
        sach.setNxb(CNNXB.getText().toString());
        sachDao sachDao = new sachDao(DetailSach.this);
        int kq = sachDao.updateSach(sach);
        if (kq==0){
            Toast.makeText(getApplicationContext(),"thatbai",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(DetailSach.this,sachActivity.class);
        startActivity(intent);

    }

    public void HuyCNSach(View view) {
        finish();
    }
    public void getCategory(){
        TheLoaiDao theLoaiDao = new TheLoaiDao(DetailSach.this);
        theLoaiList = theLoaiDao.getAllTheLoai();
        ArrayAdapter<TheLoai> arrayAdapter = new ArrayAdapter<TheLoai>(this, android.R.layout.simple_spinner_item, theLoaiList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCNS.setAdapter(arrayAdapter);
    }
}
