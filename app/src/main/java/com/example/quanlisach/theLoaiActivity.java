package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.quanlisach.Adapter.theLoaiAdapter;
import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.model.TheLoai;

import java.util.List;

public class theLoaiActivity extends AppCompatActivity {
public static List<TheLoai> lsTL;
ListView listViewTL;
TheLoaiDao theLoaiDao;
theLoaiAdapter  theLoaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        listViewTL=findViewById(R.id.lvTheLoai);
        theLoaiDao=new TheLoaiDao(theLoaiActivity.this);
        lsTL=theLoaiDao.getAllTheLoai();
        theLoaiAdapter=new theLoaiAdapter(this,lsTL);
        listViewTL.setAdapter(theLoaiAdapter);

        //
        listViewTL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(theLoaiActivity.this,DetailsTheLoai.class);
                Bundle bundle = new Bundle();
                bundle.putString("matheloai",lsTL.get(position).getMaTheLoai());
                bundle.putString("tentheloai",lsTL.get(position).getTenTheLoai());
                bundle.putString("mota",lsTL.get(position).getMoTa());
                bundle.putString("vitri",lsTL.get(position).getViTri());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public void themTheLoai(View view) {
        Intent intent = new Intent(theLoaiActivity.this, addTheLoai.class);
        startActivity(intent);
    }

    public void thoatTheLoai(View view) {
        Intent intent = new Intent(theLoaiActivity.this,menuActivity.class);
        startActivity( intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lsTL.clear();//xoa du lieu cu
        lsTL = theLoaiDao.getAllTheLoai(); //cap nhat du lieu moi
        theLoaiAdapter.changeDataset(lsTL);
    }
}
