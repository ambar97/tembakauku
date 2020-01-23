package com.example.westo.Model;

public class ListItemHama {
    String namaHama,Gambar,solusi;

    public ListItemHama(String namaHama, String gambar, String keterangan) {
        this.namaHama = namaHama;
        Gambar = gambar;
        this.solusi = keterangan;
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
