package com.example.westo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.westo.Model.ListItemPenyakit;
import com.example.westo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewAdapterPenyakit extends RecyclerView.Adapter<RecycleViewAdapterPenyakit.ViewHolder> {
    Context context;
    private List<ListItemPenyakit> listItemPenyakits;

    public RecycleViewAdapterPenyakit(Context context, List<ListItemPenyakit> listItemPenyakits) {
        this.context = context;
        this.listItemPenyakits = listItemPenyakits;
    }

    @NonNull
    @Override
    public RecycleViewAdapterPenyakit.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View penyakit= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitempenyakit,parent,false);
        return new ViewHolder(penyakit);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterPenyakit.ViewHolder holder, int position) {
    final ListItemPenyakit listItemPenyakit = listItemPenyakits.get(position);
    String namabagian = listItemPenyakit.getNama_bagian();
    String bagian="";
        if (namabagian.equals("Batang")){
            bagian = "Penyakit Batang";
        }else if(namabagian.equals("Daun")){
            bagian = "Penyakit Daun";
        }else {
            bagian = "Penyakit Akar";
        }
        holder.nama_penyakit.setText(listItemPenyakit.getNama_penyakit());
        holder.bagianPenyakit.setText(bagian);
    }

    @Override
    public int getItemCount() {
        return listItemPenyakits.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_penyakit,bagianPenyakit;
        public ImageView gambar_penyakit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_penyakit = itemView.findViewById(R.id.litsitempenyakit_text);
            bagianPenyakit = itemView.findViewById(R.id.bagiane);
            gambar_penyakit = itemView.findViewById(R.id.listitempenyakit);
        }
    }
}
