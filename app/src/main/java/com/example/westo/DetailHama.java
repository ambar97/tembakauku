package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Instant;

public class DetailHama extends AppCompatActivity {
TextView nama,ket;
ImageView imageView;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hama);
        nama = findViewById(R.id.nama);
        ket = findViewById(R.id.keterangan);
        imageView = findViewById(R.id.gambar);

        intent=getIntent();
        nama.setText(intent.getStringExtra("nama"));

    }
}
