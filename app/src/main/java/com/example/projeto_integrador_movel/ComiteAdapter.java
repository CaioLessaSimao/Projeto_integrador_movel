package com.example.projeto_integrador_movel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComiteAdapter extends RecyclerView.Adapter{

    Context context;
    List<String> diretores;

    public ComiteAdapter(Context context, List<String> diretores) {
        this.context = context;
        this.diretores = diretores;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item,parent, false);
        ComiteViewHolder viewHolder = new ComiteViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String diretor = this.diretores.get(position);

        TextView tvListItem = holder.itemView.findViewById(R.id.tvListItem);
        tvListItem.setText(diretor);
    }

    @Override
    public int getItemCount() {
        return this.diretores.size();
    }

}
