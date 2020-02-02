package com.example.westo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
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
import com.example.westo.Adapter.RecycleViewAdapterPenyakit;
import com.example.westo.Adapter.RecycleviewAdapterHama;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListItemHama;
import com.example.westo.Model.ListItemPenyakit;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data_Penyakit extends AppCompatActivity {
    FloatingActionButton home,penyakit,hama,diagnosa;
    RecycleViewAdapterPenyakit adapterPenyakit;
    List<ListItemPenyakit> itemPenyakits = new ArrayList<>();
    RecyclerView recyclerView;
    BaseUrlApiModel baseUrlApiModel=new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();
    String ApiGetPenyakit = "api/HamaPenyakit?api=penyakit";
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data__penyakit);
        setTitle("Data Penyakit");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.dataPenyakit_rec);
        recyclerView.setHasFixedSize(true);
        context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        swipeRefreshLayout = findViewById(R.id.refreshPenyakit);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadDataPenyakit();
            }
        });
        loadDataPenyakit();
    }

    private void loadDataPenyakit() {
        swipeRefreshLayout.setEnabled( true );
        itemPenyakits= new ArrayList<>();
        itemPenyakits.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + ApiGetPenyakit, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: "+response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray banyakdata = jsonObject.getJSONArray("data");
                    for (int i=0; i<banyakdata.length(); i++){
                        JSONObject datagetpenyakit = banyakdata.getJSONObject(i);
                        String aa = datagetpenyakit.getString("id_bagian");
                        String bagian="";
                        if (aa.equals("1")){
                            bagian = "Batang";
                        }else if(aa.equals("2")){
                            bagian = "Daun";
                        }else {
                            bagian = "Akar";
                        }
                        Log.d("hh", "onResponse: "+bagian);
                        ListItemPenyakit listItemPenyakite = new ListItemPenyakit(
                                datagetpenyakit.getString("nama_penyakit"),
                                bagian,
                                datagetpenyakit.getString("nama_penyebab"),
                                datagetpenyakit.getString("deskripsi"),
                        null,
                        datagetpenyakit.getString("nama_solusi")
                        );
                        itemPenyakits.add(listItemPenyakite);
                    }
                    adapterPenyakit = new RecycleViewAdapterPenyakit(context,itemPenyakits);
                    recyclerView.setAdapter(adapterPenyakit);
                    adapterPenyakit.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("P", "onErrorResponse: ",error );
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
    public void fab(){
        home = findViewById(R.id.home);
        penyakit = findViewById(R.id.penyakit);
        hama = findViewById(R.id.hama);
        diagnosa = findViewById(R.id.diagnosa);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data_Penyakit.this,MainActivity.class));
            }
        });
        penyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data_Penyakit.this,Data_Penyakit.class));
            }
        });
        hama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data_Penyakit.this,Data_Hama.class));
            }
        });
        diagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Data_Penyakit.this,Diagnosa.class));
            }
        });
    }
}
