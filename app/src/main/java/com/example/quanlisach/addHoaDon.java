package com.example.quanlisach;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlisach.dao.HoaDonDao;
import com.example.quanlisach.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class addHoaDon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
  //  private int year, month, day;
    EditText dateHD,edtmaHoaDon;
    SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hoa_don);
        dateHD= findViewById(R.id.edNgayMua);
         edtmaHoaDon=findViewById(R.id.edMaHoaDon);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar cal = new GregorianCalendar(year, month, day);
        setDate(cal);
    }
    private void setDate(final Calendar calendar){
        dateHD.setText(sdf.format(calendar.getTime()));
    }



    public static class DatePickerFragment extends DialogFragment{
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
           int year=c.get(Calendar.YEAR);
           int month=c.get(Calendar.MONTH);
           int day=c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(), year, month, day);
        }
    }

    public void datePicker(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"date");
    }
       public  int validation(){
        if(edtmaHoaDon.getText().toString().isEmpty()||dateHD.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
}
    public void ADDHoaDon(View view) {
        HoaDonDao hoaDonDao = new HoaDonDao(addHoaDon.this);
        try {
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Dien day du cho trong",Toast.LENGTH_LONG).show();
            }else {
                HoaDon hoaDon = new HoaDon(edtmaHoaDon.getText().toString(),
                                           sdf.parse(dateHD.getText().toString()));
                if (hoaDonDao.insertHoaDon(hoaDon)>0){
                    Toast.makeText(getApplicationContext(),"Them thanh cong hoa don",Toast.LENGTH_LONG).show();
                    Intent intent= new Intent(addHoaDon.this,addHoaDonChiTiet.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("MAHOADON",edtmaHoaDon.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(),"Them that bai",Toast.LENGTH_LONG).show();
                }
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
