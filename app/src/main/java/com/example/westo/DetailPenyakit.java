package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailPenyakit extends AppCompatActivity {
    TextView nama,ket,penyebab,bagian,latin,solusi;
    ImageView imageView;
    Intent intent;
    SwipeRefreshLayout swipeRefreshLayout;
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
//        imageView = findViewById(R.id.gambar);
        bagian = findViewById(R.id.bagian);
        latin = findViewById(R.id.latin);
        penyebab = findViewById(R.id.penyebab);
        solusi = findViewById(R.id.solusi);
        intent=getIntent();
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
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        nama.setText(intent.getStringExtra("nama"));
        ket.setText(intent.getStringExtra("deskripsi"));
        solusi.setText(intent.getStringExtra("solusi"));
        penyebab.setText(intent.getStringExtra("penyebab"));
        bagian.setText(intent.getStringExtra("bagian"));
        swipeRefreshLayout.setRefreshing(false);
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

}
