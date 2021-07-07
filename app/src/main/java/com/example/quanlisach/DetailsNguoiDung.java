package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlisach.dao.NguoiDungDao;
import com.example.quanlisach.model.NguoiDung;

public class DetailsNguoiDung extends AppCompatActivity {
EditText edtPhone, edtName;
String name, phone, username, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_nguoi_dung);
        edtPhone = findViewById(R.id.sdtu);
        edtName = findViewById(R.id.hoTenu);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name=bundle.getString("hoten");
        phone=bundle.getString("phone");
        username=bundle.getString("username");
        pass=bundle.getString("password");
        edtName.setText(name);
        edtPhone.setText(phone);



    }

    public void capnhatND(View view) {
        NguoiDung nguoiDung = new NguoiDung();
        nguoiDung.setUsername(username);
        nguoiDung.setPhone(edtPhone.getText().toString());
        nguoiDung.setHoten(edtName.getText().toString());
        nguoiDung.setPassword(pass);
    NguoiDungDao nguoiDungDao = new NguoiDungDao(DetailsNguoiDung.this);
    int kq= nguoiDungDao.updateNguoiDung(nguoiDung);
        if (kq==0){
        Toast.makeText(getApplicationContext(),"thatbai",Toast.LENGTH_LONG).show();
    }else {
        Toast.makeText(getApplicationContext(),"thanhcong",Toast.LENGTH_LONG).show();
    }

    Intent intent = new Intent(DetailsNguoiDung.this,nguoiDungActivity.class);
    startActivity(intent);
}

    public void huyCNND(View view) {
        finish();
    }
}
