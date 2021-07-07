package com.example.quanlisach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlisach.database.DataBaseHelper;
import com.example.quanlisach.model.sach;

import java.util.ArrayList;
import java.util.List;

public class sachDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME="Sach";
    public static final String TAG="TAG_Sach";
    public static final String SQL_Sach= "CREATE TABLE Sach (" +
            " masach text primary key, " +
            " matheloai text," +
            " tensach text, " +
            " tacgia text, " +
            " giasach double," +
            " soluong number," +
            " nxb text," +
            " soTrang number" +
            ");";

    public sachDao(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertSach(sach s){
        ContentValues values = new ContentValues();
        values.put("masach",s.getMasach());
        values.put("matheloai",s.getMaTheLoai());
        values.put("tensach",s.getTensach());
        values.put("tacgia",s.getTacgia());
        values.put("giasach",s.getGiasach());
        values.put("soluong",s.getSoluong());
        values.put("nxb",s.getNxb());
        values.put("soTrang",s.getSoTrang());
        try{
            if(db.insert(TABLE_NAME,null,values)<0){
                return -1;//insert k thanh cong
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;//insert thanh cong
    }

    public List<sach> getAllSach(){
        List<sach> lsS = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            sach s = new sach();
            s.setMasach(cursor.getString(0));
            s.setMaTheLoai(cursor.getString(1));
            s.setTensach(cursor.getString(2));
            s.setTacgia(cursor.getString(3));
            s.setGiasach(cursor.getInt(4));
            s.setSoluong(cursor.getInt(5));
            s.setNxb(cursor.getString(6));
            s.setSoTrang(cursor.getInt(7));
            lsS.add(s);
            cursor.moveToNext();
        }
        cursor.close();
        return lsS;
    }
    //xoa du lieu
    public int deleteSach(String masach){
        int kq = db.delete(TABLE_NAME,"masach = ?", new String[]{masach});
        if (kq==0){
            return -1;//xoa ko thanh cong
        }
        return 1;//xoa thanh cong
    }
    //update sach
    public int updateSach(sach s){
        ContentValues values = new ContentValues();
        values.put("masach",s.getMasach());
        values.put("matheloai",s.getMaTheLoai());
        values.put("tensach",s.getTensach());
        values.put("tacgia",s.getTacgia());
        values.put("giasach",s.getGiasach());
        values.put("soluong",s.getSoluong());
        values.put("nxb",s.getNxb());
        values.put("soTrang",s.getSoTrang());
       int kq = db.update(TABLE_NAME,values,"masach = ?", new String[]{s.getMasach()});
        if (kq==0){
            return -1;
        }
        return 1;//insert thanh cong
    }
    //check
    public sach checkbook(String strPrimaryKey){
        sach s = new sach();
        String[] columns = {"masach"};
        String selection = "masach=?";
        String[] selectionArgs = {strPrimaryKey};
        Cursor cursor = null;
        try {
            cursor= db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                s.setMasach(cursor.getString(0));
                s.setMaTheLoai(cursor.getString(1));
                s.setTensach(cursor.getString(2));
                s.setTacgia(cursor.getString(3));
                s.setGiasach(cursor.getInt(4));
                s.setSoluong(cursor.getInt(5));
                s.setNxb(cursor.getString(6));
                s.setSoTrang(cursor.getInt(7));
                break;
            }
            cursor.close();
            return s;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public sach getSachbyID(String masach){
        sach s = null;
        String selection = "masach=?";
        String[] selectionArgs = {masach};
        Cursor cursor = db.query(TABLE_NAME,null,selection,selectionArgs,null,null,null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false){
                s=new sach();
                s.setMasach(cursor.getString(0));
                s.setMaTheLoai(cursor.getString(1));
                s.setTensach(cursor.getString(2));
                s.setTacgia(cursor.getString(3));
                s.setGiasach(cursor.getInt(4));
                s.setSoluong(cursor.getInt(5));
                s.setNxb(cursor.getString(6));
                s.setSoTrang(cursor.getInt(7));
                break;
            }
            cursor.close();
            return s;
    }
   public List<sach> getSachTop10(String month){
        List<sach> dsSAch = new ArrayList<>();
        if (Integer.parseInt(month)<10){
            month="0"+month;
        }
        String sSQL = "SELECT HoaDonChiTiet.maSach, Sach.tensach,Sach.giasach, SUM(HoaDonChiTiet.soLuong) as soLuong FROM ((HoaDonChiTiet INNER JOIN HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon) INNER JOIN Sach ON HoaDonChiTiet.maSach = Sach.masach) WHERE strftime('%m',HoaDon.ngayMua) = '"+month+"' "
                + "GROUP BY HoaDonChiTiet.maSach ORDER BY soLuong DESC LIMIT 10 " ;
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            Log.d("/=====",cursor.getString(0));
            sach sach = new sach();
            sach.setMasach(cursor.getString(0));
            sach.setSoluong(cursor.getInt(3));
            sach.setGiasach(cursor.getInt(2));
            sach.setMaTheLoai("");
            sach.setTensach(cursor.getString(1));
            sach.setTacgia("");
            sach.setNxb("");
            dsSAch.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        return dsSAch;
   }

   //((HoaDonChiTiet INNER JOIN HoaDon ON HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon) INNER JOIN Sach ON HoaDonChiTiet.maSach = Sach.masach)
}
