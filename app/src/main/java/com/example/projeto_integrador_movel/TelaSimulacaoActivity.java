package com.example.projeto_integrador_movel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaSimulacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_simulacao);
    }

    public void onClickNotaTrabalhoOp(View view) {
        Intent i = new Intent(TelaSimulacaoActivity.this,NotaTrabalhoActivity.class);
        startActivity(i);
    }

    public void onClickAgendaOp(View view) {
        Intent i = new Intent(TelaSimulacaoActivity.this,NotaTrabalhoActivity.class);
        startActivity(i);
    }

    public void onClickQuestaoOp(View view) {
        Intent i = new Intent(TelaSimulacaoActivity.this,NotaTrabalhoActivity.class);
        startActivity(i);
    }
}