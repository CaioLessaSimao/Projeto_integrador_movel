package com.example.projeto_integrador_movel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PaginaComiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_comite);
    }

    public void onClickEntrar(View view) {
        Intent i = new Intent(PaginaComiteActivity.this,TelaSimulacaoActivity.class);
        startActivity(i);
    }
}