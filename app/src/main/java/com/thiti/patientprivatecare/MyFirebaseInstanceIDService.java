package com.thiti.patientprivatecare;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
    }
}
