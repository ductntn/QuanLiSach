package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;


import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.TheLoai;
import com.example.quanlisach.model.sach;

import java.util.ArrayList;
import java.util.List;


public class addSach extends AppCompatActivity {
sachDao sachDao;
EditText edtMaS, edtTenS, edtTacGia, edtGiaS, edtSoluong, edtNXB,edtST;
Spinner spinner;
List<TheLoai> theLoaiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sach);
        edtMaS = findViewById(R.id.maSach);
        edtTenS = findViewById(R.id.tenSach);
        edtTacGia = findViewById(R.id.tacGia);
        edtGiaS = findViewById(R.id.giasach);
        edtSoluong = findViewById(R.id.soLuong);
        edtNXB = findViewById(R.id.nhaXuatBan);
        spinner = findViewById(R.id.spnId);
        edtST = findViewById(R.id.soTrang);
        getCategory();

    }

    public void luuSach(View view) {
        sachDao = new sachDao(addSach.this);

        try {
            if (validation()==-1){
                Toast.makeText(getApplicationContext(),"Dien du thong tin",Toast.LENGTH_LONG).show();
            }else {
                sach u = new sach(edtMaS.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        edtTenS.getText().toString(),
                        edtTacGia.getText().toString(),
                        Integer.parseInt(edtGiaS.getText().toString()),
                        Integer.parseInt( edtSoluong.getText().toString()),
                        edtNXB.getText().toString(),
                        Integer.parseInt( edtST.getText().toString()));
            if (sachDao.insertSach(u)>0){
                Toast.makeText(getApplicationContext(),"INSERT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(getApplicationContext(),"INSERT NOT SUCCESSFUL",Toast.LENGTH_LONG).show();
            }}
        }catch (Exception e){
            Log.e("ERROR: ",e.toString());
        }
    }
    public  int validation() {
        if (edtMaS.getText().toString().isEmpty() || edtTenS.getText().toString().isEmpty() || edtGiaS.getText().toString().isEmpty() || edtTacGia.getText().toString().isEmpty() || edtSoluong.getText().toString().isEmpty()||edtNXB.getText().toString().isEmpty()) {
            return -1;
        }
        return 1;
    }

    public void HuySach(View view) {

        Intent intent = new Intent(addSach.this,menuActivity.class);
        startActivity( intent);
    }

    public void hienThiS(View view) {
        Intent intent = new Intent(addSach.this,sachActivity.class);
        startActivity(intent);
    }

    public void intentTL(View view) {
        Intent intent = new Intent(addSach.this,addTheLoai.class);
        startActivity(intent);
    }
    public void getCategory(){
        TheLoaiDao theLoaiDao = new TheLoaiDao(addSach.this);
        theLoaiList = theLoaiDao.getAllTheLoai();
        ArrayAdapter<TheLoai> arrayAdapter = new ArrayAdapter<TheLoai>(this, android.R.layout.simple_spinner_item, theLoaiList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
}
