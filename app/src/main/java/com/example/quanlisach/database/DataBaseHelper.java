package com.example.quanlisach.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//import com.example.quanlisach.dao.HoaDonChiTietDao;
//import com.example.quanlisach.dao.HoaDonDao;
import com.example.quanlisach.dao.HoaDonChiTietDao;
import com.example.quanlisach.dao.HoaDonDao;
import com.example.quanlisach.dao.NguoiDungDao;
import com.example.quanlisach.dao.TheLoaiDao;
import com.example.quanlisach.dao.sachDao;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBM";
    public static final int VERSION = 1;
    //tao database
    public DataBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
//tao bang
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDao.SQL_THE_LOAI);
        db.execSQL(sachDao.SQL_Sach);
        db.execSQL(HoaDonDao.SQL_Hoa_Don);
        db.execSQL(HoaDonChiTietDao.SQL_Hoa_Don_Chi_Tiet);
    }
//nang cap bang
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + NguoiDungDao.TABLE_NAME);
       db.execSQL("DROP TABLE IF EXISTS " + TheLoaiDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + sachDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HoaDonDao.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + HoaDonChiTietDao.TABLE_NAME);
    }
}
