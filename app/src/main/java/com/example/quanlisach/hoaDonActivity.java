package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanlisach.Adapter.hoaDonAdapter;
import com.example.quanlisach.dao.HoaDonDao;
import com.example.quanlisach.model.HoaDon;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class hoaDonActivity extends AppCompatActivity {
List<HoaDon> dsHoaDon = new ArrayList<>();
ListView lvHD;
hoaDonAdapter  hoaDonAdapter =null;
HoaDonDao hoaDonDao;
EditText edtSHD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        lvHD=findViewById(R.id.lvHoaDon);
        hoaDonDao=new HoaDonDao(hoaDonActivity.this);
        try {
            dsHoaDon=hoaDonDao.getAllHoaDon();
        } catch (Exception e) {
            e.printStackTrace();
        }
        hoaDonAdapter=new hoaDonAdapter(this,dsHoaDon);
        lvHD.setAdapter(hoaDonAdapter);
        lvHD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HoaDon hoaDon = (HoaDon)parent.getItemAtPosition(position);
                Intent intent=new Intent(hoaDonActivity.this,hoaDonChiTietAcitivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("MAHOADON",hoaDon.getMaHoaDon());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        lvHD.setTextFilterEnabled(true);
        edtSHD=findViewById(R.id.edSearchHD);
        edtSHD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"] - Start ["+start+"] - Before ["+before+"] - Count ["+count+"]");
                if (count<before){
                    hoaDonAdapter.resetData();
                }
                hoaDonAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void themHD(View view) {
        Intent intent = new Intent(hoaDonActivity.this,addHoaDon.class);
        startActivity(intent);
    }

    public void thoatHD(View view) {
        Intent intent = new Intent(hoaDonActivity.this,menuActivity.class);
        startActivity(intent);
    }


}
