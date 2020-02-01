package com.thiti.patientprivatecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PatientRegisterActivity extends AppCompatActivity {

    Button btn_insert;
    EditText txtName,txtLastname,txtAge,txtBirthdate,txtAddress,txtStatementID,txtDisease,txtMedical,txtHospital;
    String userID;
    TextView patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        btn_insert = findViewById(R.id.btn_regist);
        txtName = findViewById(R.id.name);
        txtLastname = findViewById(R.id.lastname);
        txtAge = findViewById(R.id.age);
        txtBirthdate = findViewById(R.id.birthdate);
        txtAddress = findViewById(R.id.address);
        txtStatementID = findViewById(R.id.statementID);
        txtDisease = findViewById(R.id.disease);
        txtMedical = findViewById(R.id.medical);
        txtHospital = findViewById(R.id.hospital);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertPatientData();
            }
        });
    }

    private void insertPatientData() {

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userID = bundle.getString("email");
        }

        final String name = txtName.getText().toString().trim();
        final String lastname = txtLastname.getText().toString().trim();
        final String age = txtAge.getText().toString().trim();
        final String birthdate = txtBirthdate.getText().toString().trim();
        final String address = txtAddress.getText().toString().trim();
        final String statementID = txtStatementID.getText().toString().trim();
        final String disease = txtDisease.getText().toString().trim();
        final String medical = txtMedical.getText().toString().trim();
        final String hospital = txtHospital.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        if (name.isEmpty() || lastname.isEmpty() || age.isEmpty() || birthdate.isEmpty() || address.isEmpty() || statementID.isEmpty() || disease.isEmpty() || medical.isEmpty() || hospital.isEmpty()) {
            Toast.makeText(this,"Please complete a form", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://61.19.104.75/medical/save_patient.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equalsIgnoreCase("Success")){
                                Toast.makeText(PatientRegisterActivity.this,"Data Inserted, Please login again.", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                Intent intent = new Intent(PatientRegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(PatientRegisterActivity.this,response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PatientRegisterActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("name",name);
                    params.put("surname",lastname);
                    params.put("age",age);
                    params.put("birthdate",birthdate);
                    params.put("address",address);
                    params.put("state_id",statementID);
                    params.put("disease",disease);
                    params.put("medicine",medical);
                    params.put("hospital",hospital);
                    params.put("user_id", userID);

                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(PatientRegisterActivity.this);
            requestQueue.add(request);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
