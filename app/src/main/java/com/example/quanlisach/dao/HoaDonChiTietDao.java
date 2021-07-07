package com.example.quanlisach.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.quanlisach.database.DataBaseHelper;
import com.example.quanlisach.model.HoaDon;
import com.example.quanlisach.model.HoaDonChiTiet;
import com.example.quanlisach.model.sach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class HoaDonChiTietDao {
    private SQLiteDatabase db;
    private SQLiteOpenHelper dbHelper;
    public static final String TABLE_NAME="HoaDonChiTiet";
    public static final String TAG="TAG_HoaDonChiTiet";
    public static final String SQL_Hoa_Don_Chi_Tiet = "CREATE TABLE HoaDonChiTiet (" +
            " maHoaDonChiTiet INTEGER primary key AUTOINCREMENT, " +
            " maHoaDon text NOT NULL," +
            " maSach text NOT NULL," +
            " soLuong INTEGER" +
            ");";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public HoaDonChiTietDao(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int insertHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet){
        ContentValues values = new ContentValues();
        values.put("maHoaDon",hoaDonChiTiet.getHoaDon().getMaHoaDon());
        values.put("maSach",hoaDonChiTiet.getSach().getMasach());
        values.put("soLuong",hoaDonChiTiet.getSoLuongMua());
        try{
            if(db.insert(TABLE_NAME,null,values)<0){
                return -1;//insert k thanh cong
            }
        }catch (Exception e){
            Log.e(TAG,e.toString());
        }
        return 1;//insert thanh cong
    }
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(){
        List<HoaDonChiTiet> lsHDCT = new ArrayList<>();
        String sSQL = "SELECT maHoaDonChiTiet, HoaDon.maHoaDon,HoaDon.ngayMua, " +
                "Sach.masach,Sach.matheloai,Sach.tensach,Sach.tacgia,Sach.giasach,Sach.nxb, " +
                "Sach.soluong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.masach = HoaDonChiTiet.maSach" ;

        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        try {
        while (cursor.isAfterLast()==false){
            HoaDonChiTiet s = new HoaDonChiTiet();
            s.setMaHoaDonCT(cursor.getInt(0));
            s.setHoaDon(new HoaDon(cursor.getString(1),sdf.parse(cursor.getString(2))));
            s.setSach(new sach(cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getInt(10)));
            s.setSoLuongMua(cursor.getInt(10));
            lsHDCT.add(s);
            cursor.moveToNext();
            }
        cursor.close();
        }
        catch (ParseException e) {
                e.printStackTrace();
            }
  return lsHDCT;
    }
    public List<HoaDonChiTiet> getAllHoaDonChiTietByID(String maHoaDon){
        List<HoaDonChiTiet> lsHDCT = new ArrayList<>();
        String sSQL = "SELECT maHoaDonChiTiet, HoaDon.maHoaDon,HoaDon.ngayMua, "+
                "Sach.masach,Sach.matheloai,Sach.tensach,Sach.tacgia,Sach.giasach,Sach.nxb, "+
                "Sach.soluong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
                "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on " +
                "Sach.masach = HoaDonChiTiet.maSach where HoaDonChiTiet.maHoaDon='"+maHoaDon+"' " ;

        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        try {
            while (cursor.isAfterLast()==false){
                HoaDonChiTiet s = new HoaDonChiTiet();
                s.setMaHoaDonCT(cursor.getInt(0));
                s.setHoaDon(new HoaDon(cursor.getString(1),sdf.parse(cursor.getString(2))));
                s.setSach(new sach(cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getInt(7),cursor.getInt(8),cursor.getString(9),cursor.getInt(10)));
                s.setSoLuongMua(cursor.getInt(10));
                lsHDCT.add(s);
                cursor.moveToNext();
            }
            cursor.close();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return lsHDCT;
    }


    //xoa du lieu
    public int deleteHoaDonCT(String maHoaDonChiTiet){
        int kq = db.delete(TABLE_NAME,"maHoaDonChiTiet = ?", new String[]{maHoaDonChiTiet});
        if (kq==0){
            return -1;//xoa ko thanh cong
        }
        return 1;//xoa thanh cong
    }

    //update sach
    public int updateHoaDonChiTiet(HoaDonChiTiet s){
        ContentValues values = new ContentValues();
        values.put("maHoaDonChiTiet",s.getMaHoaDonCT());
        values.put("maHoaDon",s.getHoaDon().getMaHoaDon());
        values.put("maSach",s.getSach().getMasach());
        values.put("soLuong",s.getSoLuongMua());
        int kq = db.update(TABLE_NAME,values,"maHoaDonChiTiet = ?", new String[]{String.valueOf(s.getMaHoaDonCT())});
        if (kq==0){
            return -1;
        }
        return 1;//insert thanh cong
    }
    //check
    public boolean checkHoaDon(String maHoaDon){
        //select
        String[] columns = {"maHoaDon"};
        //where clause
        String selection = "maHoaDon=?";
        String[] selectionArgs ={maHoaDon};
        Cursor cursor =null;
        try {
          cursor=db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
          cursor.moveToFirst();
          int i = cursor.getCount();
          cursor.close();
          if (i<=0){
              return false;
          }
          return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public int getDoanhThuTheoNgay(){
        int doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giasach * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.masach where HoaDon.ngayMua = date('now') " +
                "GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu=cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public int getDoanhThuTheoThang(){
        int doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giasach * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.masach where strftime('%m',HoaDon.ngayMua) = " +
                "strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu=cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
    public int getDoanhThuTheoNam(){
        int doanhThu = 0;
        String sSQL = "SELECT SUM(tongtien) from (SELECT SUM(Sach.giasach * HoaDonChiTiet.soLuong) as 'tongtien' " +
                "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
                "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.masach where strftime('%Y',HoaDon.ngayMua) " +
                "= strftime('%Y','now')  GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor cursor = db.rawQuery(sSQL,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false){
            doanhThu=cursor.getInt(0);
            cursor.moveToNext();
        }
        cursor.close();
        return doanhThu;
    }
}
