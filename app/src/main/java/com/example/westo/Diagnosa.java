package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.westo.Adapter.RecycleviewAdapterGejala;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListItemGejala;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Diagnosa extends AppCompatActivity {
    CardView akar,batang,daun;
    FloatingActionButton home,penyakit,hama,diagnosa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);
        setTitle("Diagnosa");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        akar = findViewById(R.id.akar);
        batang = findViewById(R.id.batang);
        daun = findViewById(R.id.daun);
        fab();
        akar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Diagnosa.this, detail_diagnosa.class);
                intent.putExtra("bagian","1");
                intent.putExtra("diagnosa","1");
                finish();
                startActivity(intent);
            }
        });
        batang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Diagnosa.this, detail_diagnosa.class);
                intent.putExtra("bagian","2");
                intent.putExtra("diagnosa","2");
                finish();
                startActivity(intent);
            }
        });
        daun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Diagnosa.this, detail_diagnosa.class);
                intent.putExtra("bagian","3");
                intent.putExtra("diagnosa","3");
//                finish();
                startActivity(intent);
            }
        });
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
                startActivity(new Intent(Diagnosa.this,MainActivity.class));
            }
        });
        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diagnosa.this,Data_Penyakit.class));
            }
        });
        hama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diagnosa.this,Data_Hama.class));
            }
        });
        diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Diagnosa.this,Diagnosa.class));
            }
        });
    }
}
