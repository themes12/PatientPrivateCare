package com.thiti.patientprivatecare;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.HashMap;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";
    DatabaseReference databaseDate;
    FirebaseDatabase Node;
    SessionManager sessionManager;
    String getId;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.EMAIL);
        Node = FirebaseDatabase.getInstance();
        databaseDate = Node.getReference("users");
        Token token = new Token(refreshedToken);

        databaseDate.child(getId).setValue(token);
    }
}
