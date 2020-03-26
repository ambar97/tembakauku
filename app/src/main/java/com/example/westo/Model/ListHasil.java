package com.example.westo.Model;

public class ListHasil {
    String nama_penyakit,nama_bagian,penyebab,keterangan,gambar,solusi;

    public ListHasil(String nama_penyakit, String nama_bagian, String penyebab, String keterangan, String gambar, String solusi) {
        this.nama_penyakit = nama_penyakit;
        this.nama_bagian = nama_bagian;
        this.penyebab = penyebab;
        this.keterangan = keterangan;
        this.gambar = gambar;
        this.solusi = solusi;
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

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }
}
