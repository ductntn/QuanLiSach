package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quanlisach.Adapter.sachAdapter;
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.sach;

import java.util.ArrayList;
import java.util.List;

public class banChayActivity extends AppCompatActivity {
public static List<sach> dsSach = new ArrayList<>();
ListView lvSachBC;
sachAdapter sachAdapter;
sachDao sachDao;
EditText thang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_chay);
        lvSachBC = findViewById(R.id.lvBookTop);
        thang = findViewById(R.id.edThang);

    }

    public void VIEW_SACH_TOP_10(View view) {
        if (Integer.parseInt(thang.getText().toString())>13||Integer.parseInt(thang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"ko dung dinh dang thang 1-12",Toast.LENGTH_LONG).show();
        }else {
            sachDao = new sachDao(banChayActivity.this);
            dsSach = sachDao.getSachTop10(thang.getText().toString());
            sachAdapter=new sachAdapter(this,dsSach);
            lvSachBC.setAdapter(sachAdapter);
        }
    }
}
