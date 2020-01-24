package com.example.westo.Model;

public class ListItemGejala {
    String namaGejala,nomor;

    public String getNomor() {
        return nomor;
    }

    public ListItemGejala(String namaGejala, String nomor) {
        this.namaGejala = namaGejala;
        this.nomor = nomor;
    }

    public ListItemGejala(String namaGejala) {
        this.namaGejala = namaGejala;
    }

    public String getNamaGejala() {
        return namaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        this.namaGejala = namaGejala;
    }
}
