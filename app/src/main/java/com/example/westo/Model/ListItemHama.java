package com.example.westo.Model;

public class ListItemHama {
    String namaHama,Gambar,solusi,bagian,penyebab,keterangan;

    public ListItemHama(String namaHama, String gambar, String solusi, String bagian, String penyebab, String keterangan) {
        this.namaHama = namaHama;
        Gambar = gambar;
        this.solusi = solusi;
        this.bagian = bagian;
        this.penyebab = penyebab;
        this.keterangan = keterangan;
    }

    public String getBagian() {
        return bagian;
    }

    public void setBagian(String bagian) {
        this.bagian = bagian;
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

    public String getNamaHama() {
        return namaHama;
    }

    public void setNamaHama(String namaHama) {
        this.namaHama = namaHama;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        Gambar = gambar;
    }

    public String getSolusi() {
        return solusi;
    }

    public void setSolusi(String solusi) {
        this.solusi = solusi;
    }
}
