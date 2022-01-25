package com.example.projeto_integrador_movel;



import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FCM", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("FCM", "Message data payload: " + remoteMessage.getData());
            Notification.
                    getInstance().
                    addAction(remoteMessage.getData().get("action"), remoteMessage.getData().get("idcomite"), remoteMessage.getData().get("nome"));


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("FCM", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    public static class PsgdAction {
        public String action;
        public String idComite;
        public String nomeDel;
    }

    public static class Notification {
        private static Notification instance;
        private MutableLiveData<PsgdAction> newAction;

        private Notification() {
            newAction = new MutableLiveData<>();
        }

        public static Notification getInstance() {
            if(instance == null){
                instance = new Notification();
            }
            return instance;
        }

        public LiveData<PsgdAction> getNewAction() {
            return newAction;
        }

        public void addAction(String action, String idComite, String nomeDel){
            PsgdAction psgd = new PsgdAction();
            psgd.action = action;
            psgd.idComite = idComite;
            psgd.nomeDel = nomeDel;
            newAction.postValue(psgd);

        }
    }

}