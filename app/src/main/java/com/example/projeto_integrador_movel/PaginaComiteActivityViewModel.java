package com.example.projeto_integrador_movel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaginaComiteActivityViewModel extends AndroidViewModel {
    MutableLiveData<String> nomeComiteLV = new MutableLiveData<>();
    MutableLiveData<String> temaComiteLV = new MutableLiveData<>();
    MutableLiveData<String> DiretorGLV = new MutableLiveData<>();
    MutableLiveData<List<String>> diretoresLV = new MutableLiveData<>();
    boolean sim = false;

    public PaginaComiteActivityViewModel(Application application) {
        super(application);
    }

    public void carregarDados(String idComite){
        String login = Config.getLogin(getApplication());
        String senha = Config.getPassword(getApplication());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.SERVER_URL_BASE + "comiteMobile.php", "GET", "UTF-8");
                httpRequest.setBasicAuth(login,senha);
                httpRequest.addParam("idComite", idComite);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Util.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");

                    if(success == 1){
                        String nome = jsonObject.getString("nomeComite");
                        String tema = jsonObject.getString("temaComite");
                        String dirG = jsonObject.getString("dirG");
                        JSONArray dirAss = jsonObject.getJSONArray("dirAss");
                        List<String> diretores = new ArrayList<>();
                        for (int i=0; i<dirAss.length();i++){
                            JSONObject jDiretor = dirAss.getJSONObject(i);
                            diretores.add(jDiretor.getString("nome"));
                        }
                        nomeComiteLV.postValue(nome);
                        temaComiteLV.postValue(tema);
                        DiretorGLV.postValue(dirG);
                        diretoresLV.postValue(diretores);
                    }


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public LiveData<String> getNomeComiteLV() {
        return nomeComiteLV;
    }

    public LiveData<String> getTemaComiteLV() {
        return temaComiteLV;
    }

    public LiveData<List<String>> getDiretoresLV() {
        return diretoresLV;
    }

    public LiveData<String> getDiretorGLV() { return DiretorGLV; }

}
