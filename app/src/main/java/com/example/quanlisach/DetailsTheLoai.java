package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.model.TheLoai;

public class DetailsTheLoai extends AppCompatActivity {
EditText CNTenTl,CNViTriTL,CNMoTaTL;
String CtenTL, CViTriTL, CMoTaTL, MTL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_the_loai);
        CNTenTl = findViewById(R.id.CNtenTL);
        CNViTriTL = findViewById(R.id.CNviTri);
        CNMoTaTL = findViewById(R.id.CNmoTa);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        CtenTL=bundle.getString("tentheloai");
        CViTriTL=bundle.getString("vitri");
        CMoTaTL=bundle.getString("mota");
        MTL=bundle.getString("matheloai");

        CNTenTl.setText(CtenTL);
        CNViTriTL.setText(CViTriTL);
        CNMoTaTL.setText(CMoTaTL);
    }

    public void CNTL(View view) {
        TheLoai theLoai = new TheLoai();
        theLoai.setMaTheLoai(MTL);
        theLoai.setTenTheLoai(CNTenTl.getText().toString());
        theLoai.setViTri(CNViTriTL.getText().toString());
        theLoai.setMoTa(CNMoTaTL.getText().toString());

        TheLoaiDao theLoaiDao = new TheLoaiDao(DetailsTheLoai.this);
        int kq= theLoaiDao.updateTheLoai(theLoai);
        if (kq==0){
            Toast.makeText(getApplicationContext(),"thatbai",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent(DetailsTheLoai.this,theLoaiActivity.class);
        startActivity(intent);
    }

    public void huyCNTL(View view) {
        finish();
    }
}
