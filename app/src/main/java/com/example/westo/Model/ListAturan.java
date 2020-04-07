package com.example.westo.Model;

public class ListAturan {

    String nama_gejala,gambar,id_gejala,id_penyakit;

    public ListAturan(String nama_gejala, String gambar, String id_gejala, String id_penyakit) {
        this.nama_gejala = nama_gejala;
        this.gambar = gambar;
        this.id_gejala = id_gejala;
        this.id_penyakit = id_penyakit;
    }

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }
}
