package com.example.westo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.westo.Model.BaseUrlApiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        branda = findViewById(R.id.beranda);
        ambildata(intent.getStringExtra("id_penyakit"));
        Log.d("id_penyakit", "onCreate: "+intent.getStringExtra("id_penyakit"));

        branda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(detailDiagnosaGo.this,MainActivity.class));
            }
        });

    }
    private void ambildata(String id_penyakit){
        String ApiGethasil = "api/diagnosa?api=ambilhasil&penyakit="+id_penyakit;

        nmpenyakit = findViewById(R.id.nm_penyakit);
        penyebab = findViewById(R.id.penyebab);
        solusi = findViewById(R.id.solusi);
        deskripsi = findViewById(R.id.deskripsi);
        gambar = findViewById(R.id.gambar_);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + ApiGethasil, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: " + response);
                try {
                    String tipe;
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray banyakdata = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < banyakdata.length(); i++) {
                    JSONObject datagetgejala = banyakdata.getJSONObject(0);
                    String nm_penyakit = datagetgejala.getString("nama_penyakit");
                    String bagian= datagetgejala.getString("nama_bagian");
                    String penyebab_get = datagetgejala.getString("nama_penyebab");
                    String solus_get= datagetgejala.getString("nama_solusi");
                    String tipe_get = datagetgejala.getString("tipe");
                    String deskripsi_get = datagetgejala.getString("deskripsi");
                    String gambar_get = datagetgejala.getString("gambar");
//                    }


                    if (tipe_get.equals("1")){
                        tipe = "Hama ";
                    }else {
                        tipe="Penyakit ";
                    }
                    nmpenyakit.setText(tipe+" "+nm_penyakit);
                    penyebab.setText(penyebab_get);
                    solusi.setText(Html.fromHtml(solus_get));
                    deskripsi.setText(Html.fromHtml(deskripsi_get));
                    if (!gambar_get.equals("")){
                        Glide.with(detailDiagnosaGo.this)
                                // LOAD URL DARI INTERNET
                                .load(baseUrl+gambar_get)
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambar);
                    }else {
                        Glide.with(detailDiagnosaGo.this)
                                // LOAD URL DARI INTERNET
                                .load("https://tby.jogjaprov.go.id/booking/assets/image/no-image-available.jpg")
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambar);
                    }

                    Log.d("ggghghgh", "onResponse: " + datagetgejala);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("P", "onErrorResponse: ", error);
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
