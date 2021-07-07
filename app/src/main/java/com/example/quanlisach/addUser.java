package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlisach.dao.NguoiDungDao;
import com.example.quanlisach.model.NguoiDung;

public class addUser extends AppCompatActivity {
Button buttonThemNguoiDung,btnHienThi;
NguoiDungDao nguoiDungDao;
EditText edtUser, edtPass, edtRePass, edtPhone, edtFullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        buttonThemNguoiDung = findViewById(R.id.btnadduser);
        edtUser = findViewById(R.id.tenDN);
        edtPass = findViewById(R.id.matKhau);
        edtRePass = findViewById(R.id.NLmatKhau);
        edtPhone = findViewById(R.id.sdt);
        edtFullName = findViewById(R.id.hoTen);
    }

    public void luuUser(View view) {
        nguoiDungDao = new NguoiDungDao(addUser.this);

        try {
            if (validation()==-1){
                Toast.makeText(getApplicationContext(),"Dien du thong tin",Toast.LENGTH_LONG).show();
            }else if (validation()==0){
                Toast.makeText(getApplicationContext(),"Password khong dong nhat",Toast.LENGTH_LONG).show();
            }else {
                NguoiDung u = new NguoiDung(edtUser.getText().toString(),
                        edtPass.getText().toString(),
                        edtPhone.getText().toString(),
                        edtFullName.getText().toString());
            if (nguoiDungDao.insertNguoiDung(u)>0){
                Toast.makeText(getApplicationContext(),"INSERT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"INSERT NOT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }}
        }catch (Exception e){
            Log.e("ERROR: ",e.toString());
        }

    }
    public  int validation() {
        if (edtUser.getText().toString().isEmpty() || edtPass.getText().toString().isEmpty() || edtRePass.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty() || edtFullName.getText().toString().isEmpty()) {
            return -1;
        }if ( !edtPass.getText().toString().equalsIgnoreCase(edtRePass.getText().toString())){
            return 0;
        }
        return 1;
    }
    public void HuyUser(View view) {
        Intent intent = new Intent(addUser.this,menuActivity.class);
        startActivity( intent);
    }

    public void hienThiND(View view) {
        Intent intent = new Intent(addUser.this,nguoiDungActivity.class);
        startActivity(intent);
    }

}
