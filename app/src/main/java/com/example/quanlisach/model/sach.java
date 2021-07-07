package com.example.quanlisach.model;

import androidx.annotation.NonNull;

public class sach {
    private String masach;
    private String maTheLoai;
    private String tensach;
    private String tacgia;
    private int giasach;
    private int soluong;
    private String nxb;
    private int soTrang;

    public sach(String masach, String maTheLoai, String tensach, String tacgia, int giasach, int soluong, String nxb,int soTrang) {
        this.masach = masach;
        this.maTheLoai = maTheLoai;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.giasach = giasach;
        this.soluong = soluong;
        this.nxb = nxb;
        this.soTrang = soTrang;
    }

    public sach() {
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    public String getMasach() {
        return masach;
    }

    public void setMasach(String masach) {
        this.masach = masach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getGiasach() {
        return giasach;
    }

    public void setGiasach(int giasach) {
        this.giasach = giasach;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }


}
