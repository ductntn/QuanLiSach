
package com.example.quanlisach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.quanlisach.Adapter.sachAdapter;
import com.example.quanlisach.dao.sachDao;
import com.example.quanlisach.model.sach;

import java.util.List;

public class sachActivity extends AppCompatActivity {
public static List<sach> lsS;
ListView listViewS;
sachDao sachDao;
sachAdapter sachAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        listViewS=findViewById(R.id.lvSach);
        sachDao=new sachDao(sachActivity.this);
        lsS =sachDao.getAllSach();
        sachAdapter=new sachAdapter(this,lsS);
        listViewS.setAdapter(sachAdapter);

        //
        listViewS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(sachActivity.this,DetailSach.class);
                Bundle bundle = new Bundle();
                bundle.putString("masach",lsS.get(position).getMasach());
                bundle.putString("matheloai",lsS.get(position).getMaTheLoai());
                bundle.putString("tensach",lsS.get(position).getTensach());
                bundle.putString("tacgia",lsS.get(position).getTacgia());
                bundle.putString("giasach",String.valueOf(lsS.get(position).getGiasach()));
                bundle.putString("soluong", String.valueOf(lsS.get(position).getSoluong()));
                bundle.putString("nxb",lsS.get(position).getNxb());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

       listViewS.setTextFilterEnabled(true);
        EditText edtTimSach = findViewById(R.id.edtTimSach);
        edtTimSach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count<before){
                    sachAdapter.resetData1();
                }
                sachAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void themSach(View view) {
        Intent intent = new Intent(sachActivity.this, addSach.class);
        startActivity(intent);
    }

    public void thoatSach(View view) {
        Intent intent = new Intent(sachActivity.this,menuActivity.class);
        startActivity( intent);
    }

    //cap nhat

    @Override
    protected void onResume() {
        super.onResume();
        lsS.clear();
        lsS = sachDao.getAllSach();
        sachAdapter.changeDataset(lsS);
    }
}
