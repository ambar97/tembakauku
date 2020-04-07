package com.example.westo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.westo.DetailPenyakit;
import com.example.westo.Model.BaseUrlApiModel;
import com.example.westo.Model.ListHasil;
import com.example.westo.Model.ListItemPenyakit;
import com.example.westo.R;
import com.example.westo.detailDiagnosaGo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleviewAdapterHasil extends RecyclerView.Adapter<RecycleviewAdapterHasil.ViewHolder> {
    Context context;
    private List<ListHasil> listHasils;
    BaseUrlApiModel baseUrlApiModel=new BaseUrlApiModel();
    String baseUrl = baseUrlApiModel.getBaseURL();

    public RecycleviewAdapterHasil(Context context, List<ListHasil> listHasils) {
        this.context = context;
        this.listHasils = listHasils;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View w = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hasil,parent,false);
        return new ViewHolder(w);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListHasil listHasil= listHasils.get(position);
        holder.nama_penyakit.setText(listHasil.getNama_penyakit());
        if (!listHasil.getGambar().equals("")){
            Glide.with(context)
                    // LOAD URL DARI INTERNET
                    .load(baseUrl+listHasil.getGambar())
                    // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                    .into(holder.gambar_penyakit);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, detailDiagnosaGo.class);
                intent.putExtra("nama",listHasil.getNama_penyakit());
                intent.putExtra("solusi",listHasil.getSolusi());
                intent.putExtra("bagian",listHasil.getNama_bagian());
                intent.putExtra("deskripsi",listHasil.getKeterangan());
                intent.putExtra("penyebab",listHasil.getPenyebab());
                intent.putExtra("gambar",listHasil.getGambar());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listHasils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_penyakit;
        public CircleImageView gambar_penyakit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_penyakit = itemView.findViewById(R.id.litsitempenyakit_text);
            gambar_penyakit = itemView.findViewById(R.id.listitempenyakit);
        }
    }
}
