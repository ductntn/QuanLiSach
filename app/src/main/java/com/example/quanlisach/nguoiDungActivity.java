package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlisach.Adapter.nguoiDungAdapter;
import com.example.quanlisach.dao.NguoiDungDao;
import com.example.quanlisach.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class nguoiDungActivity extends AppCompatActivity {
public static List<NguoiDung> lsND;
ListView listView;
NguoiDungDao nguoiDungDao;
nguoiDungAdapter ndAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        listView = findViewById(R.id.lvuser);
        nguoiDungDao=new NguoiDungDao(nguoiDungActivity.this);
        lsND =nguoiDungDao.getAllNguoiDung();
        ndAdapter=new nguoiDungAdapter(this,lsND);
        listView.setAdapter(ndAdapter);

        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(nguoiDungActivity.this,DetailsNguoiDung.class);
                Bundle bundle = new Bundle();
                bundle.putString("username",lsND.get(position).getUsername());
                bundle.putString("password",lsND.get(position).getPassword());
                bundle.putString("phone",lsND.get(position).getPhone());
                bundle.putString("hoten",lsND.get(position).getHoten());
                  intent.putExtras(bundle);
                  startActivity(intent);
            }
        });

    }

    public void themUser(View view) {
        Intent intent = new Intent(nguoiDungActivity.this, addUser.class);
        startActivity(intent);
    }

    public void thoatUser(View view) {
        Intent intent = new Intent(nguoiDungActivity.this,menuActivity.class);
        startActivity( intent);
    }


    //cap nhat
    @Override
    protected void onResume() {
        super.onResume();
        lsND.clear();//xoa du lieu cu
        lsND = nguoiDungDao.getAllNguoiDung(); //cap nhat du lieu moi
        ndAdapter.changeSataset(lsND);
    }
}
