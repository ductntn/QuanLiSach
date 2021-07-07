package com.example.quanlisach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlisach.database.DataBaseHelper;
import com.example.quanlisach.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME="TheLoai";
    public static final String TAG="TAG_TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (" +
            " matheloai text primary key, " +
            " tentheloai text," +
            " vitri text," +
            " mota text" +
            ");";

    public TheLoaiDao(Context context){
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertTheLoai(TheLoai n){
        ContentValues values = new ContentValues();
        values.put("matheloai",n.getMaTheLoai());
        values.put("tentheloai",n.getTenTheLoai());
        values.put("vitri",n.getViTri());
        values.put("mota",n.getMoTa());
        try{
            if(db.insert(TABLE_NAME,null,values)<0){
                return -1;//insert k thanh cong
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;//insert thanh cong
    }

    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> lsND = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            TheLoai tl = new TheLoai();
            tl.setMaTheLoai(cursor.getString(0));
            tl.setTenTheLoai(cursor.getString(1));
            tl.setViTri(cursor.getString(2));
            tl.setMoTa(cursor.getString(3));
            lsND.add(tl);
          //  Log.d("===",tl.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return lsND;
    }
    //xoa du lieu
    public int deleteTheLoai(String matheloai){
        int kq = db.delete(TABLE_NAME,"matheloai = ?", new String[]{matheloai});
        if (kq==0){
            return -1;//xoa ko thanh cong
        }
        return 1;//xoa thanh cong
    }
    public int updateTheLoai(TheLoai tl){
        ContentValues values = new ContentValues();
        values.put("matheloai",tl.getMaTheLoai());
        values.put("tentheloai",tl.getTenTheLoai());
        values.put("vitri",tl.getViTri());
        values.put("mota",tl.getMoTa());
        int kq = db.update(TABLE_NAME,values,"matheloai=?", new String[]{tl.getMaTheLoai()});
        if (kq==0){
            return -1;
        }
        return 1;
    }
}
