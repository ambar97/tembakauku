package com.example.westo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.westo.Model.ListItemGejala;
import com.example.westo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleviewAdapterGejala extends RecyclerView.Adapter<RecycleviewAdapterGejala.ViewHolder> {
    private List<ListItemGejala> listItemGejalas;
    Context context;

    public RecycleviewAdapterGejala(List<ListItemGejala> listItemGejalas, Context context) {
        this.listItemGejalas = listItemGejalas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View w = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemgejala,parent,false);
        return new ViewHolder(w);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListItemGejala listGejala = listItemGejalas.get(position);
        holder.namaGajala.setText(listGejala.getNamaGejala());
        holder.nomor.setText(listGejala.getNomor());
    }

    @Override
    public int getItemCount() {
        return listItemGejalas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaGajala,nomor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaGajala = itemView.findViewById(R.id.txt_namaGejala);
            nomor = itemView.findViewById(R.id.nomor);
        }
    }
}
