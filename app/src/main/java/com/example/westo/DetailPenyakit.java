package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westo.Model.BaseUrlApiModel;
import com.github.clans.fab.FloatingActionButton;

public class DetailPenyakit extends AppCompatActivity {
    TextView nama, ket, penyebab, bagian, latin, solusi;
    FloatingActionButton home, penyakit, hama, diagnosa;
    ImageView imageView;
    Intent intent;

    SwipeRefreshLayout swipeRefreshLayout;
    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);
        setTitle("Detail Penyakit");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nama = findViewById(R.id.judul);
        ket = findViewById(R.id.deskripsi);
        imageView = findViewById(R.id.gambar);

        bagian = findViewById(R.id.bagian);
        latin = findViewById(R.id.latin);
        penyebab = findViewById(R.id.penyebab);
        solusi = findViewById(R.id.solusi);
        intent = getIntent();
        final String alamatGambar = intent.getStringExtra("gambar");
        swipeRefreshLayout = findViewById(R.id.refresh);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nama.setText(intent.getStringExtra("nama"));
                ket.setText(intent.getStringExtra("deskripsi"));
                solusi.setText(intent.getStringExtra("solusi"));
                penyebab.setText(intent.getStringExtra("penyebab"));
                bagian.setText(intent.getStringExtra("bagian"));
                if (!alamatGambar.equals("")) {
                    Glide.with(DetailPenyakit.this)
                            // LOAD URL DARI INTERNET
                            .load(baseUrl + alamatGambar)
                            // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                            .into(imageView);
                }
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        nama.setText(intent.getStringExtra("nama"));
        ket.setText(intent.getStringExtra("deskripsi"));
        solusi.setText(Html.fromHtml(intent.getStringExtra("solusi")));
        penyebab.setText(intent.getStringExtra("penyebab"));
        bagian.setText(intent.getStringExtra("bagian"));

        if (!alamatGambar.equals("")) {
            Glide.with(this)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl + alamatGambar)
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(imageView);
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void fab() {
        home = findViewById(R.id.home);
        penyakit = findViewById(R.id.penyakit);
        hama = findViewById(R.id.hama);
        diagnosa = findViewById(R.id.diagnosa);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPenyakit.this, MainActivity.class));
            }
        });
        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPenyakit.this, Data_Penyakit.class));
            }
        });
        hama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPenyakit.this, Data_Hama.class));
            }
        });
        diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailPenyakit.this, Diagnosa.class));
            }
        });
    }


}
