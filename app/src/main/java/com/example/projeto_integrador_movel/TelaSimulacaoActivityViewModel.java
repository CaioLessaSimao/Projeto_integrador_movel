package com.example.projeto_integrador_movel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class TelaSimulacaoActivityViewModel extends AndroidViewModel {
    MutableLiveData<String> nomeComiteLV = new MutableLiveData<>();
    MutableLiveData<String> temaComiteLV = new MutableLiveData<>();
    MutableLiveData<String> proximoLV = new MutableLiveData<>();
    MutableLiveData<List<String>> lstOLV = new MutableLiveData<>();

    public TelaSimulacaoActivityViewModel(@NonNull  Application application) {
        super(application);
    }

    public LiveData<String> getNomeComiteLV() {
        return nomeComiteLV;
    }

    public LiveData<String> getTemaComiteLV() {
        return temaComiteLV;
    }

    public LiveData<String> getProximoLV() {
        return proximoLV;
    }

    public LiveData<List<String>> getLstOLV() {
        return lstOLV;
    }

}
