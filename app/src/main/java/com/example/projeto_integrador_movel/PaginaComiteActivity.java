package com.example.projeto_integrador_movel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

//TUDO CERTO
public class PaginaComiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_comite);

        LiveData<MyFirebaseMessagingService.PsgdAction> psgdActionLiveData = MyFirebaseMessagingService.Notification.getInstance().getNewAction();
        psgdActionLiveData.observe(this, new Observer<MyFirebaseMessagingService.PsgdAction>() {
            @Override
            public void onChanged(MyFirebaseMessagingService.PsgdAction psgdAction) {
                String action = psgdAction.action;
            }
        });

        Toolbar tbPagComit = findViewById(R.id.tbPagComit);
        setSupportActionBar(tbPagComit);

        Intent intent = getIntent();

        int id = Integer.parseInt(intent.getSerializableExtra("id").toString());

        String idComite = Integer.toString(id);

        PaginaComiteActivityViewModel vm = new ViewModelProvider(this).get(PaginaComiteActivityViewModel.class);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tbpagcomit,menu);
        return true;
    }

    public void onClickEntrar(View view) {
        Intent i = new Intent(PaginaComiteActivity.this,TelaSimulacaoActivity.class);
        startActivity(i);
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