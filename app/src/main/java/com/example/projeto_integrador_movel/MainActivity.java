package com.example.projeto_integrador_movel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPaginaComite(View view) {
        Intent i = new Intent(MainActivity.this,PaginaComiteActivity.class);
        startActivity(i);
    }
}