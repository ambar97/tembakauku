package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westo.Model.BaseUrlApiModel;

public class detailDiagnosaGo extends AppCompatActivity {
Intent intent;
TextView nmpenyakit,bagian,solusi,penyebab,deskripsi;
ImageView gambar;
Button branda,diagnosa;
    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);
        setTitle("Hasil Diagnosa");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent=getIntent();
        nmpenyakit = findViewById(R.id.nm_penyakit);
        penyebab = findViewById(R.id.penyebab);
        solusi = findViewById(R.id.solusi);
        deskripsi = findViewById(R.id.deskripsi);
        gambar = findViewById(R.id.gambar_);
        branda = findViewById(R.id.beranda);
        String tipe;
        String getTipe = intent.getStringExtra("tipe");
        if (getTipe.equals("1")){
            tipe = "Hama ";
        }else {
            tipe="Penyakit ";
        }
        nmpenyakit.setText(tipe+intent.getStringExtra("penyakit"));
        penyebab.setText(intent.getStringExtra("penyebab"));
        solusi.setText(Html.fromHtml(intent.getStringExtra("solusi")));
        deskripsi.setText(Html.fromHtml(intent.getStringExtra("deskripsi")));
        if (intent.getStringExtra("gambar").equals("")){
            Glide.with(detailDiagnosaGo.this)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl+intent.getStringExtra("gambar"))
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(gambar);
        }else {
            Glide.with(detailDiagnosaGo.this)
                    // LOAD URL DARI INTERNET
                    .load("https://tby.jogjaprov.go.id/booking/assets/image/no-image-available.jpg")
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(gambar);
        }

        branda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(detailDiagnosaGo.this,MainActivity.class));
            }
        });
    }
}
