package com.example.westo.Model;

public class ListItemPenyakit {
    String nama_penyakit,nama_bagian,penyebab,solusi,gambar;

    public ListItemPenyakit(String nama_penyakit, String nama_bagian, String penyebab, String solusi, String gambar) {
        this.nama_penyakit = nama_penyakit;
        this.nama_bagian = nama_bagian;
        this.penyebab = penyebab;
        this.solusi = solusi;
        this.gambar = gambar;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getNama_bagian() {
        return nama_bagian;
    }

    public void setNama_bagian(String nama_bagian) {
        this.nama_bagian = nama_bagian;
    }

    public String getPenyebab() {
        return penyebab;
    }

    public void setPenyebab(String penyebab) {
        this.penyebab = penyebab;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
