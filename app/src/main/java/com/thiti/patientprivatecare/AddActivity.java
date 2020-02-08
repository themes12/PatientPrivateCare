package com.thiti.patientprivatecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    EditText txtHour,txtMinute;
    Button btn_send;
    SessionManager sessionManager;
    String userID;
    DatabaseReference databaseDate;
    FirebaseDatabase Node;
    private Spinner mThaiSpinner;
    String select;

    private ArrayList<String> mThaiClub = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtHour = findViewById(R.id.txtHour);
        txtMinute = findViewById(R.id.txtMin);
        btn_send = findViewById(R.id.btn_addMedical);

        mThaiSpinner = (Spinner) findViewById(R.id.select);

        createThaiClubData();

        // Adapter ตัวแรก
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, mThaiClub);
        mThaiSpinner.setAdapter(adapterThai);

        mThaiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = mThaiClub.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(AddActivity.this, "You need to select something", Toast.LENGTH_SHORT).show();
            }
        });

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

        Date date = new Date(userID,select,sendHour,sendMin);

        databaseDate.child(userID).child(key).setValue(date);
        Toast.makeText(AddActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        Intent intent = new Intent(AddActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void createThaiClubData() {

        mThaiClub.add("schedule");
        mThaiClub.add("Appointment time");
    }
}
