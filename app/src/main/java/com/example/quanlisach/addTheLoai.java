package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.model.NguoiDung;
import com.example.quanlisach.model.TheLoai;

public class addTheLoai extends AppCompatActivity {
TheLoaiDao theLoaiDao;
EditText edtMaTL, edtTenTL, edtViTriTL, edtMoTaTL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_the_loai);
        edtMaTL=findViewById(R.id.maTL);
        edtTenTL=findViewById(R.id.tenTL);
        edtViTriTL=findViewById(R.id.viTri);
        edtMoTaTL=findViewById(R.id.moTa);
    }

    public void luuTL(View view) {
        theLoaiDao =  new TheLoaiDao(addTheLoai.this);

        try {
            if (validation()==-1){
                Toast.makeText(getApplicationContext(),"Dien du thong tin",Toast.LENGTH_LONG).show();
            }else {
                TheLoai u = new TheLoai(edtMaTL.getText().toString(),
                        edtTenTL.getText().toString(),
                        edtViTriTL.getText().toString(),
                        edtMoTaTL.getText().toString());
            if (theLoaiDao.insertTheLoai(u)>0){
                Toast.makeText(getApplicationContext(),"INSERT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"INSERT NOT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }}
        }catch (Exception e){
            Log.e("ERROR: ",e.toString());
        }
    }
    public  int validation() {
        if (edtMaTL.getText().toString().isEmpty() || edtTenTL.getText().toString().isEmpty() || edtViTriTL.getText().toString().isEmpty() || edtMoTaTL.getText().toString().isEmpty() ) {
            return -1;
        }
        return 1;
    }

    public void huyTL(View view) {
        Intent intent = new Intent(addTheLoai.this,menuActivity.class);
        startActivity( intent);
    }

    public void hienThiTL(View view) {
        Intent intent = new Intent(addTheLoai.this,theLoaiActivity.class);
        startActivity(intent);
    }
}
