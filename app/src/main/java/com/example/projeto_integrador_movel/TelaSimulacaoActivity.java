package com.example.projeto_integrador_movel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//TUDO CERTO
public class TelaSimulacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_simulacao);

        Intent i = getIntent();
        String nomeComite = i.getSerializableExtra("nomeComite").toString();
        String temaComite = i.getSerializableExtra("temaComite").toString();

        TelaSimulacaoActivityViewModel vm = new ViewModelProvider(this).get(TelaSimulacaoActivityViewModel.class);
        vm.nomeComiteLV.postValue(nomeComite);
        vm.temaComiteLV.postValue(temaComite);

        LiveData<String> nomeComiteLV = vm.getNomeComiteLV();
        nomeComiteLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView tvNomCom = findViewById(R.id.tvNomeCom);
                tvNomCom.setText(s);
            }
        });

        LiveData<String> temaComiteLV = vm.getTemaComiteLV();
        temaComiteLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                TextView tvTemaCom = findViewById(R.id.tvTemaCom);
                tvTemaCom.setText(s);
            }
        });

        LiveData<String> proximoLV = vm.getProximoLV();
        proximoLV.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                vm.proximoLV.postValue(s);
                TextView tvPrx = findViewById(R.id.tvPrx);
                String res = "Discursando agora: " + s;
                tvPrx.setText(res);
            }
        });

        RecyclerView rvLstO = findViewById(R.id.rvLstO);
        rvLstO.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvLstO.setLayoutManager(layoutManager);

        LiveData<List<String>> oradoresLV = vm.getLstOLV();
        oradoresLV.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                TelaSimulacaoAdapter telaSimulacaoAdapter = new TelaSimulacaoAdapter(TelaSimulacaoActivity.this, strings);
                rvLstO.setAdapter(telaSimulacaoAdapter);
            }
        });

        LiveData<MyFirebaseMessagingService.PsgdAction> psgdActionLiveData = MyFirebaseMessagingService.Notification.getInstance().getNewAction();
        psgdActionLiveData.observe(this, new Observer<MyFirebaseMessagingService.PsgdAction>() {
            @Override
            public void onChanged(MyFirebaseMessagingService.PsgdAction psgdAction) {
                String action = psgdAction.action;
                String nomeDel = psgdAction.nomeDel;
                if(action.equals("adicionar")){
                    //verificar se array esta vazio
                    if(oradoresLV.getValue() == null){
                        List<String> oradores = new ArrayList<>();
                        oradores.add(nomeDel);
                        vm.lstOLV.postValue(oradores);
                    }
                    else{
                        List<String> oradores = vm.lstOLV.getValue();
                        oradores.add(nomeDel);
                        vm.lstOLV.postValue(oradores);
                    }
                }
                else if(action.equals("proximo")){
                    if(oradoresLV.getValue() != null){
                        List<String> oradores = vm.lstOLV.getValue();
                        String proximo = nomeDel;
                        oradores.remove(0);
                        vm.lstOLV.postValue(oradores);
                        vm.proximoLV.postValue(proximo);
                    }
                }
            }
        });
    }


}