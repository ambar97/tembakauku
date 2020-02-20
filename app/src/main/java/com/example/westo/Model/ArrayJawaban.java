package com.example.westo.Model;

public class ArrayJawaban {
    String id_gejala,id_penyakit;

    public ArrayJawaban(String id_gejala, String id_penyakit) {
        this.id_gejala = id_gejala;
        this.id_penyakit = id_penyakit;
    }

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }
}
