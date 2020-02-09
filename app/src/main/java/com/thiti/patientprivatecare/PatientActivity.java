package com.thiti.patientprivatecare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientActivity extends AppCompatActivity {

    private static final String TAG = PatientActivity.class.getSimpleName(); //getting the info
    private TextView name, email;
    private Button btn_photo_upload;
    SessionManager sessionManager;
    String getId;
    private static String URL_READ = "http://61.19.104.75/medical/read_detail.php?role=patient";
    private static String URL_EDIT = "http://61.19.104.75/medical/edit_detail.php?role=patient";
    private static String URL_UPLOAD = "http://61.19.104.75/medical/upload.php?role=patient";
    private Menu action;
    private Bitmap bitmap;
    CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

    }
}
