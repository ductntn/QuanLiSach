package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void viewNguoiDung(View view) {
        Intent intent = new Intent(menuActivity.this, nguoiDungActivity.class);
        startActivity(intent);
    }

    public void viewThongKe(View view) {
        Intent intent = new Intent(menuActivity.this, thongKeActivity.class);
        startActivity(intent);
    }

    public void viewBanChay(View view) {Intent intent = new Intent(menuActivity.this, banChayActivity.class);
        startActivity(intent);
    }

    public void viewHoaDon(View view) {Intent intent = new Intent(menuActivity.this,hoaDonActivity.class);
        startActivity(intent);
    }

    public void viewSach(View view) {Intent intent = new Intent(menuActivity.this, sachActivity.class);
        startActivity(intent);
    }

    public void viewTheLoai(View view) {Intent intent = new Intent(menuActivity.this, theLoaiActivity.class);
        startActivity(intent);
    }
}
