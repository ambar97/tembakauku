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
    TextView nomorsoal, soal,keterangan;
    Button ya, tidak, selesai;
    RecycleviewAdapterHasil adapterHasil;
    String bagian, jenis;
    RecyclerView recyclerView;
    Context context;
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
    int urut = 1;
//    ProgressDialog progressDialog=new ProgressDialog(this);

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
        recyclerView = findViewById(R.id.dataHasilDiagnosa);
        recyclerView.setLayoutManager(new LinearLayoutManager(detail_diagnosa.this));
        keterangan = findViewById(R.id.keterangan);
        linearLayout2 = findViewById(R.id.lay2);
        linearLayout1 = findViewById(R.id.linerGambar);
        bagian = intent.getStringExtra("bagian");
        nomorsoal = findViewById(R.id.nosoal);
        soal = findViewById(R.id.soal);
        gambarGejala = findViewById(R.id.gambar_gejala);
        ya = findViewById(R.id.ya);
        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tidak = findViewById(R.id.tidak);
        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        selesai = findViewById(R.id.selesai);
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
                nomorsoal.setVisibility(View.VISIBLE);
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

    private void soal(final List<ListAturan> listSoal) {
        final int i = 0;
        if (!listSoal.get(i).getGambar().equals("")){
            Glide.with(this)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl+listSoal.get(i).getGambar())
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(gambarGejala);
        }else {
            Glide.with(detail_diagnosa.this)
                    // LOAD URL DARI INTERNET
                    .load("https://tby.jogjaprov.go.id/booking/assets/image/no-image-available.jpg")
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(gambarGejala);

        }
        soal.setText("Apakah " + listSoal.get(i).getNama_gejala()+" ?");
        nomorsoal.setText("Soal Ke-" + urut+" / "+listSoal.size());
        ya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayJawaban arrayJawaban = new ArrayJawaban(listSoal.get(counter).getId_gejala(),listSoal.get(counter).getId_penyakit());
                jawabans.add(arrayJawaban);
                counter++;
                urut++;
                if (counter >= listSoal.size()){
                    ya.setVisibility(View.GONE);
                    tidak.setVisibility(View.GONE);
                    selesai.setVisibility(View.VISIBLE);
                }else {
                    if (!listSoal.get(counter).getGambar().equals("")){
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI INTERNET
                                .load(baseUrl+listSoal.get(counter).getGambar())
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);
                    }else {
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI INTERNET
                                .load("https://tby.jogjaprov.go.id/booking/assets/image/no-image-available.jpg")
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);

                    }
                    nomorsoal.setText("Soal Ke-" + urut+" / "+listSoal.size());
                    soal.setText("Apakah " + listSoal.get(counter).getNama_gejala()+" ?");
                }
            }
        });
        tidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;urut++;
                if (counter >= listSoal.size()){
                    ya.setVisibility(View.GONE);
                    tidak.setVisibility(View.GONE);
                    selesai.setVisibility(View.VISIBLE);
                }else {
                    if (!listSoal.get(counter).getGambar().equals("")){
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI INTERNET
                                .load(baseUrl+listSoal.get(counter).getGambar())
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);
                    }else {
                        Glide.with(detail_diagnosa.this)
                                // LOAD URL DARI INTERNET
                                .load("https://tby.jogjaprov.go.id/booking/assets/image/no-image-available.jpg")
                                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                                .into(gambarGejala);

                    }
                    nomorsoal.setText("Soal Ke-" + urut+" / "+listSoal.size());
                    soal.setText("Apakah "+listSoal.get(counter).getNama_gejala()+" ?");
                }
            }
        });


        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                nomorsoal.setVisibility(View.GONE);
//                gambarGejala.setVisibility(View.GONE);
//                ya.setVisibility(View.GONE);
//                tidak.setVisibility(View.GONE);
                selesai.setVisibility(View.GONE);
//                soal.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                Toast.makeText(detail_diagnosa.this,"Cie Nunggu Jawaban ya ? :v",Toast.LENGTH_LONG).show();
                kirimgejala(jawabans);
            }
        });
    }

    //kirim gejala ke server dan get hasil query server
    private void kirimgejala(final List<ArrayJawaban> jawabanList) {
        String ApiPost = "api/diagnosa";
        listHasils = new ArrayList<>();
//        listHasils.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, baseUrl + ApiPost, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray data = jsonObject.getJSONArray("pilihan");

                    //tampilkan data pilihan dari server
                    if (data.length() == 0){
                        keterangan.setText("Penyakit tidak di temukan !");
                    }

                    for (int i=0; i<data.length(); i++){
                        JSONObject datagetpenyakit = data.getJSONObject(i);
                        ListHasil hasil = new ListHasil(datagetpenyakit.getString("nama_penyakit"),
                                "",
                                "",
                                "",
                                datagetpenyakit.getString("gambar_penyakit"),
                                "");
                        listHasils.add(hasil);
                    }
                    adapterHasil = new RecycleviewAdapterHasil(detail_diagnosa.this,listHasils);
                    recyclerView.setAdapter(adapterHasil);
                    adapterHasil.notifyDataSetChanged();
                    Log.d("isilist","Onres"+data);
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
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                JSONObject jsonObject =new JSONObject();
                JSONObject jsonObject1 =new JSONObject();
                try {
                    for (int i =0; i< jawabanList.size();i++){
                        jsonObject.put("data"+i,jawabanList.get(i).getId_gejala());
                        jsonObject1.put("penyakit"+i,jawabanList.get(i).getId_penyakit());
                    }
                }catch (JSONException e){

                }
                Map<String, String> params = new HashMap<>();
                params.put("data",jsonObject.toString());
                params.put("penyakit",jsonObject1.toString());
                params.put("jumlah",String.valueOf(jawabanList.size()));
                params.put("bagian",bagian);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
//        refreshLayout.setEnabled(true);
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
