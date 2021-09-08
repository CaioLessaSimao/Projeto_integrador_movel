package com.example.projeto_integrador_movel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//TUDO CERTO
public class PaginaComiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_comite);

        Toolbar tbPagComit = findViewById(R.id.tbPagComit);
        setSupportActionBar(tbPagComit);
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