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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.westo.Adapter.RecycleviewAdapterHama;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListItemHama;
import com.github.clans.fab.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Data_Hama extends AppCompatActivity {
    RecycleviewAdapterHama adapterHama;
    List<ListItemHama> itemHama = new ArrayList<>();
    RecyclerView recyclerView;
    BaseUrlApiModel baseUrlApiModel = new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();
    String ApiGetHama = "api/HamaPenyakit?api=hama";
    Context context;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from listview_main.xml
        setContentView(R.layout.activity_data__hama);
        setTitle("Data Hama");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.dataHama_rec);
        recyclerView.setHasFixedSize(true);
        context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        swipeRefreshLayout = findViewById(R.id.refreshHama);
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadDataHama();
            }
        });
        loadDataHama();
    }

    private void loadDataHama() {
        swipeRefreshLayout.setEnabled(true);
        itemHama = new ArrayList<>();
        itemHama.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, baseUrl + ApiGetHama, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("F", "onResponse: " + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray banyakdata = jsonObject.getJSONArray("data");
                    for (int i = 0; i < banyakdata.length(); i++) {
                        JSONObject datagethama = banyakdata.getJSONObject(i);
                        ListItemHama listItemHama = new ListItemHama(
                                datagethama.getString("nama_penyakit"),
                                datagethama.getString("gambar"),
                                datagethama.getString("nama_solusi"),
                                datagethama.getString("id_bagian"),
                                datagethama.getString("nama_penyebab"),
                                datagethama.getString("deskripsi")
                        );
                        itemHama.add(listItemHama);
                    }
                    adapterHama = new RecycleviewAdapterHama(context, itemHama);
                    recyclerView.setAdapter(adapterHama);
                    adapterHama.notifyDataSetChanged();

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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
