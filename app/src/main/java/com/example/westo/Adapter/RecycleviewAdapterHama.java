package com.example.westo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westo.DetailHama;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListItemHama;
import com.example.westo.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleviewAdapterHama extends RecyclerView.Adapter<RecycleviewAdapterHama.ViewHolder> {
    Context context;
    private List<ListItemHama> listItemHamas;
    BaseUrlApiModel baseUrlApiModel=new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();

    public RecycleviewAdapterHama(Context context, List<ListItemHama> listItemHamas) {
        this.context = context;
        this.listItemHamas = listItemHamas;

    }

    @NonNull
    @Override
    public RecycleviewAdapterHama.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View w = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemhama,parent,false);
        return new ViewHolder(w);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleviewAdapterHama.ViewHolder holder, int position) {
        final ListItemHama listHama = listItemHamas.get(position);
        holder.namaHama.setText(listHama.getNamaHama());
        if (!listHama.getGambar().equals("")){
            Glide.with(context)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl+listHama.getGambar())
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(holder.gambarHama);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHama.class);
                intent.putExtra("nama",listHama.getNamaHama());
                intent.putExtra("solusi",listHama.getSolusi());
                intent.putExtra("bagian",listHama.getBagian());
                intent.putExtra("deskripsi",listHama.getKeterangan());
                intent.putExtra("penyebab",listHama.getPenyebab());
                intent.putExtra("gambar",listHama.getGambar());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemHamas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaHama;
        public CircleImageView gambarHama;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaHama = itemView.findViewById(R.id.litsitemhama_text);
            gambarHama = itemView.findViewById(R.id.listitemhama_image);
        }
    }
}
