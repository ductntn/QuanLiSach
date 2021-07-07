package com.example.quanlisach.model;

import androidx.annotation.NonNull;

public class HoaDonChiTiet {
    private int maHoaDonCT;
    private HoaDon hoaDon;
    private sach sach;
    private int soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHoaDonCT, HoaDon hoaDon, sach sach, int soLuongMua) {
        this.maHoaDonCT = maHoaDonCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHoaDonCT() {
        return maHoaDonCT;
    }

    public void setMaHoaDonCT(int maHoaDonCT) {
        this.maHoaDonCT = maHoaDonCT;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public sach getSach() {
        return sach;
    }

    public void setSach(sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @NonNull
    @Override
    public String toString() {
        return "HoaDonChiTiet{"+
                "maHoaDonCT="+ maHoaDonCT +
                ", HoaDon=" +hoaDon.toString()+
                ", Sach=" +sach.toString()+
                ", SoLuongMua=" +soLuongMua+
                '}';

    }
}
