package com.example.projeto_integrador_movel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TelaSimulacaoAdapter extends RecyclerView.Adapter {

    Context context;
    List<String> oradores;

    public TelaSimulacaoAdapter(Context context, List<String> oradores) {
        this.context = context;
        this.oradores = oradores;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item,parent, false);
        TelaSimulacaoViewHolder viewHolder = new TelaSimulacaoViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String orador = this.oradores.get(position);

        TextView tvListItem = holder.itemView.findViewById(R.id.tvListItem);
        tvListItem.setText(orador);
    }

    @Override
    public int getItemCount() {
        return this.oradores.size();
    }
}
