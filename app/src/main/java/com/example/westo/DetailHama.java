package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westo.Model.BaseUrlApiModel;
import com.github.clans.fab.FloatingActionButton;

import java.time.Instant;

public class DetailHama extends AppCompatActivity {
    FloatingActionButton home,penyakit,hama,diagnosa;
TextView nama,ket,penyebab,bagian,latin,solusi;
ImageView imageView;
Intent intent;
    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hama);
        setTitle("Detail Hama");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nama = findViewById(R.id.judul);
        ket = findViewById(R.id.deskripsi);
        fab();
        imageView = findViewById(R.id.gambar);
        bagian = findViewById(R.id.bagian);
        latin = findViewById(R.id.latin);
        penyebab = findViewById(R.id.penyebab);
        solusi = findViewById(R.id.solusi);
        intent=getIntent();
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setRefreshing(true);
        String bagianTanaman=intent.getStringExtra("bagian");
        final String isibagian;
        if (bagianTanaman.equals("1")){
            isibagian = "Akar";
        }else if(bagianTanaman.equals("2")){
            isibagian = "Batang";
        }else {
            isibagian = "Daun";
            ;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                nama.setText(intent.getStringExtra("nama"));
                ket.setText(intent.getStringExtra("deskripsi"));
                solusi.setText(intent.getStringExtra("solusi"));
                penyebab.setText(intent.getStringExtra("penyebab"));
                bagian.setText(isibagian);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        nama.setText(intent.getStringExtra("nama"));
        ket.setText(intent.getStringExtra("deskripsi"));
        solusi.setText(intent.getStringExtra("solusi"));
        penyebab.setText(intent.getStringExtra("penyebab"));
        bagian.setText(isibagian);
        String gambarUrl = intent.getStringExtra("gambar");
        swipeRefreshLayout.setRefreshing(false);
        if (!gambarUrl.equals("")){
            Glide.with(this)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl+gambarUrl)
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(imageView);
        }


    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void fab(){
        home = findViewById(R.id.home);
        penyakit = findViewById(R.id.penyakit);
        hama = findViewById(R.id.hama);
        diagnosa = findViewById(R.id.diagnosa);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailHama.this,MainActivity.class));
            }
        });
        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailHama.this,Data_Penyakit.class));
            }
        });
        hama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailHama.this,Data_Hama.class));
            }
        });
        diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailHama.this,Diagnosa.class));
            }
        });
    }
}
