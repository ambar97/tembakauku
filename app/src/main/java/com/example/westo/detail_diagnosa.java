package com.example.westo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.westo.Adapter.RecycleviewAdapterGejala;
import com.example.westo.Adapter.RecycleviewAdapterHasil;
import com.example.westo.Model.ArrayJawaban;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListAturan;
import com.example.westo.Model.ListHasil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detail_diagnosa extends AppCompatActivity {
    Intent intent;
    ImageView gambarGejala;
    TextView id, soal;
    Button ya, tidak;
    String bagian;
    LinearLayout linearLayout1, linearLayout2;
    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();
    String ApiGetakar = "api/diagnosa?api=diagnosa&bagian=1";
    String ApiGetBatang = "api/diagnosa?api=diagnosa&bagian=2";
    String ApiGetDaun = "api/diagnosa?api=diagnosa&bagian=3";
    String ApiGet;
    List<ArrayJawaban> jawabans = new ArrayList<>();
    List<ListAturan> listAturans = new ArrayList<>();
    List<ListHasil> listHasils = new ArrayList<>();

    int counter = 0;
    int urut = 0;
    int urut_kirim = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_diagnosa);
        setTitle("Diagnosa");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        intent = getIntent();
        linearLayout1 = findViewById(R.id.linerGambar);
        id = findViewById(R.id.id_gejala);
        bagian = intent.getStringExtra("bagian");
        soal = findViewById(R.id.soal);
        gambarGejala = findViewById(R.id.gambar_gejala);
        ya = findViewById(R.id.ya);
        tidak = findViewById(R.id.tidak);
        loadDataGejala();
    }

    private void loadDataGejala() {
        if (bagian.equals("1")) {
            ApiGet = ApiGetakar;
        } else if (bagian.equals("2")) {
            ApiGet = ApiGetBatang;
        } else {
            ApiGet = ApiGetDaun;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + ApiGet, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: " + response);
                ya.setVisibility(View.VISIBLE);
                tidak.setVisibility(View.VISIBLE);
                soal.setVisibility(View.VISIBLE);
                gambarGejala.setVisibility(View.VISIBLE);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray banyakdata = jsonObject.getJSONArray("data");
                    for (int i = 0; i < banyakdata.length(); i++) {
                        JSONObject datagetgejala = banyakdata.getJSONObject(i);
                        ListAturan listAturan = new ListAturan(datagetgejala.getString("nama_gejala"),
                                datagetgejala.getString("gambar_gejala"),
                                datagetgejala.getString("id_gejala"),
                                datagetgejala.getString("id_penyakit"));
                        listAturans.add(listAturan);
                    }
                    soal(listAturans);
                    Log.d("ggghghgh", "onResponse: " + listAturans);
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

    //menampilkan soal yang sudah di ambil dari api server
    private void soal(final List<ListAturan> listSoal) {
        if (counter >= listSoal.size()) {
            counter--;
            ambilhasil(listSoal.get(counter).getId_penyakit());
        } else {
            Glide.with(this)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl + listSoal.get(counter).getGambar())
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(gambarGejala);

            id.setText(listSoal.get(counter).getId_gejala());
            soal.setText("Apakah " + listSoal.get(counter).getNama_gejala() + " ?");
        }
        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayJawaban arrayJawaban = new ArrayJawaban(listSoal.get(counter).getId_gejala(), listSoal.get(counter).getId_penyakit());
                jawabans.add(arrayJawaban);
                urut++;
                if (urut == 1) {
                    if (counter >= listSoal.size()) {
                        counter--;
                        ambilhasil(listSoal.get(counter).getId_penyakit());
                        finish();
                    } else {
                        listAturans.clear();
                        int urutan = jawabans.size();
                        counter = urutan;
                        kirimgejala(jawabans);
                        urut_kirim++;
                    }
                } else {
                    counter++;
                    if (counter >= listSoal.size()) {
                        counter--;
                        ambilhasil(listSoal.get(counter).getId_penyakit());
                        finish();
                    } else {
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI INTERNET
                                .load(baseUrl + listSoal.get(counter).getGambar())
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);
                        id.setText(listSoal.get(counter).getId_gejala());
                        soal.setText("Apakah " + listSoal.get(counter).getNama_gejala() + " ?");
                    }
                }
            }
        });

        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (urut_kirim != 1) {
                    counter++;
                    if (listSoal.size() == counter) {
                        tidak_ditemukan();
                    } else {
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI DB
                                .load(baseUrl + listSoal.get(counter).getGambar())
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);
                        id.setText(listSoal.get(counter).getId_gejala());
                        soal.setText("Apakah " + listSoal.get(counter).getNama_gejala() + " ?");
                    }
                } else {
                    if (listSoal.size() == counter) {
                        tidak_ditemukan();
                    } else {
                        listAturans.clear();
                        int urutan = jawabans.size();
                        counter = urutan;
                        kirimgejala(jawabans);
                    }

                }
            }
        });
    }

    private void ambilhasil(String id_penyakit) {
        Intent intent = new Intent(detail_diagnosa.this, detailDiagnosaGo.class);
        intent.putExtra("id_penyakit", id_penyakit);
        startActivity(intent);
        finish();
    }

    //kirim gejala ke server dan get hasil query server
    private void kirimgejala(final List<ArrayJawaban> jawabanList) {
        String ApiPost = "api/diagnosa";
        listHasils = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + ApiPost, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("kode");
                    if (data.length() == 0) {
                        tidak_ditemukan();
                    } else {
                        //tampilkan data pilihan dari server
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject datagetgejala = data.getJSONObject(i);
                            ListAturan listAturan = new ListAturan(datagetgejala.getString("nama_gejala"),
                                    datagetgejala.getString("gambar_gejala"),
                                    datagetgejala.getString("id_gejala"),
                                    datagetgejala.getString("id_penyakit"));
                            listAturans.add(listAturan);
                        }
                        soal(listAturans);
                    }
                    Log.d("counter", "counter" + counter);
                    Log.d("isilist", "Onres" + data);
                    Log.d("ggghghgh", "onResponse: " + listAturans);
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
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonObject1 = new JSONObject();
                try {
                    for (int i = 0; i < jawabanList.size(); i++) {
                        jsonObject.put("data" + i, jawabanList.get(i).getId_gejala());
                        jsonObject1.put("penyakit" + i, jawabanList.get(i).getId_penyakit());
                    }
                } catch (JSONException e) {

                }
                Map<String, String> params = new HashMap<>();
                params.put("data", jsonObject.toString());
                params.put("penyakit", jsonObject1.toString());
                params.put("api", String.valueOf(jawabanList.size()));
                params.put("bagian", bagian);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void tidak_ditemukan() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Penyakit Tidak Ditemukan !");
        builder.setMessage("Tidak ada penyakit terdeteksi dari gejala yang anda pilih.");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(detail_diagnosa.this, Diagnosa.class);
                startActivity(home);
                finish();
            }
        }, 3000);
    }


    @Override
    public boolean onSupportNavigateUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Apakah Anda Yakin Ingin Keluar ?");
        builder.setMessage("Jika anda keluar maka beberapa proses dalam halaman ini akan di gagalkan !");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent = new Intent(detail_diagnosa.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        return false;
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Apakah Anda Yakin Ingin Keluar ?");
        builder.setMessage("Jika anda keluar maka beberapa proses dalam halaman ini akan di gagalkan !");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                Intent intent = new Intent(detail_diagnosa.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
