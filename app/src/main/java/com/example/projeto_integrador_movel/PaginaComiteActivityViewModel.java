package com.example.projeto_integrador_movel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaginaComiteActivityViewModel extends ViewModel {
    MutableLiveData<String> nomeComite;
    MutableLiveData<String> temaComite;
    MutableLiveData<List<String>> diretores;
    String idDelegacao;

    public PaginaComiteActivityViewModel() {
        carregarDados();
    }

    public void carregarDados(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "comiteMobile.php", "GET", "UTF-8");
                httpRequest.addParam("idDelegacao", idDelegacao);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public LiveData<String> getNomeComiteLV() {
        return nomeComite;
    }

    public LiveData<String> getTemaComiteLV() {
        return temaComite;
    }

    public LiveData<List<String>> getDiretoresLV() {
        return diretores;
    }

    public void setIdDelegacao(String idDelegacao) {
        this.idDelegacao = idDelegacao;
    }
}
