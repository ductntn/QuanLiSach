package com.example.quanlisach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlisach.database.DataBaseHelper;
import com.example.quanlisach.model.HoaDon;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME="HoaDon";
    public static final String TAG="TAG_HoaDon";
    public static final String SQL_Hoa_Don = "CREATE TABLE HoaDon (" +
            " maHoaDon text primary key, " +
            " ngayMua date" +
            ");";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public HoaDonDao(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hoaDon.getMaHoaDon());
        values.put("ngayMua",sdf.format(hoaDon.getNgayMua()));
        try{
            if(db.insert(TABLE_NAME,null,values)<0){
                return -1;//insert k thanh cong
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;//insert thanh cong
    }
    public List<HoaDon> getAllHoaDon() throws ParseException {
        List<HoaDon> lsHD = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            HoaDon s = new HoaDon();
            s.setMaHoaDon(cursor.getString(0));
            s.setNgayMua(sdf.parse(cursor.getString(1)));
            lsHD.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return lsHD;
    }
    //xoa du lieu
    public int deleteHoaDon(String maHoaDon){
        int kq = db.delete(TABLE_NAME,"maHoaDon = ?", new String[]{maHoaDon});
        if (kq==0){
            return -1;//xoa ko thanh cong
        }
        return 1;//xoa thanh cong
    }

    //update sach
    public int updateHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hoaDon.getMaHoaDon());
        values.put("ngayMua",hoaDon.getNgayMua().toString());
        int kq = db.update(TABLE_NAME,values,"maHoaDon = ?", new String[]{hoaDon.getMaHoaDon()});
        if (kq==0){
            return -1;
        }
        return 1;//insert thanh cong
    }
}
