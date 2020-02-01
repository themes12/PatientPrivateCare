package com.thiti.patientprivatecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText txtHour,txtMinute;
    Button btn_send;
    SessionManager sessionManager;
    String userID;
    DatabaseReference databaseDate;
    FirebaseDatabase Node;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        txtHour = findViewById(R.id.txtHour);
        txtMinute = findViewById(R.id.txtMin);
        btn_send = findViewById(R.id.btn_addMedical);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveMedicalData();
            }
        });
    }

    private void saveMedicalData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        Node = FirebaseDatabase.getInstance();
        databaseDate = Node.getReference("medical");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userID = bundle.getString("email");
        }
        String sendHour = txtHour.getText().toString().trim();
        String sendMin = txtMinute.getText().toString().trim();
        String key = Node.getReference("quiz").push().getKey();

        Date date = new Date(userID,sendHour,sendMin);

        databaseDate.child(userID).child(key).setValue(date);
        Toast.makeText(AddActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
