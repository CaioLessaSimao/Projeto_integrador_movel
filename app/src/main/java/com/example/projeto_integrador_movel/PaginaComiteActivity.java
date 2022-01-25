package com.example.projeto_integrador_movel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

//TUDO CERTO
public class PaginaComiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_comite);

        Button btnSimulacao = findViewById(R.id.btnSimulacao);

        Toolbar tbPagComit = findViewById(R.id.tbPagComit);
        setSupportActionBar(tbPagComit);

        Intent intent = getIntent();

        int id = Integer.parseInt(intent.getSerializableExtra("id").toString());

        String idComite = Integer.toString(id);

        PaginaComiteActivityViewModel vm = new ViewModelProvider(this).get(PaginaComiteActivityViewModel.class);

        LiveData<MyFirebaseMessagingService.PsgdAction> psgdActionLiveData = MyFirebaseMessagingService.Notification.getInstance().getNewAction();
        psgdActionLiveData.observe(this, new Observer<MyFirebaseMessagingService.PsgdAction>() {
            @Override
            public void onChanged(MyFirebaseMessagingService.PsgdAction psgdAction) {
                String action = psgdAction.action;
                String idcomite = psgdAction.idComite;
                if (action.equals("iniciar") && idcomite.equals(idComite)) {
                    vm.sim = true;
                    btnSimulacao.setEnabled(true);
                } else {
                    vm.sim = false;
                }

            }
        });

        if (vm.sim == false) {
            btnSimulacao.setEnabled(false);
        }


        vm.carregarDados(idComite);

        LiveData<String> nomeComiteLV = vm.getNomeComiteLV();

        nomeComiteLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView tvNomeComite = findViewById(R.id.tvNomeComite);
                tvNomeComite.setText(s);
            }
        });

        LiveData<String> temaComiteLV = vm.getTemaComiteLV();

        temaComiteLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView tvTemaComite = findViewById(R.id.tvTemaComite);
                tvTemaComite.setText(s);
            }
        });

        LiveData<String> dirGLV = vm.getDiretorGLV();

        dirGLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView tvDirG = findViewById(R.id.tvDirG);
                tvDirG.setText(s);
            }
        });
        LiveData<List<String>> diretoresLV = vm.getDiretoresLV();

        RecyclerView rvDirAss = findViewById(R.id.rvDirAss);
        rvDirAss.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDirAss.setLayoutManager(layoutManager);
        diretoresLV.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                ComiteAdapter comiteAdapter = new ComiteAdapter(PaginaComiteActivity.this, strings);
                rvDirAss.setAdapter(comiteAdapter);
            }
        });

        Button entrarSim = findViewById(R.id.btnSimulacao);
        entrarSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PaginaComiteActivity.this, TelaSimulacaoActivity.class);
                i.putExtra("nomeComite", nomeComiteLV.getValue());
                i.putExtra("temaComite", temaComiteLV.getValue());
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tbpagcomit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        //Isso é um "if"
        switch (item.getItemId()){
            //Isso é a "condição do if"
            case R.id.dpo:
                Intent i = new Intent(PaginaComiteActivity.this,DpoActivity.class);
                startActivity(i);
                return true;
            case R.id.lstMes:
                Intent j = new Intent(PaginaComiteActivity.this,MessageActivity.class);
                startActivity(j);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}