package com.example.projeto_integrador_movel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//TUDO CERTO
public class NotaTrabalhoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_trabalho);

        Toolbar tbNotas = findViewById(R.id.tbNotas);
        setSupportActionBar(tbNotas);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tbnotas,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        //Isso é um "if"
        switch (item.getItemId()){
            //Isso é a "condição do if"
            case R.id.btVoltarNotas:
                Intent i = new Intent(NotaTrabalhoActivity.this,TelaSimulacaoActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}