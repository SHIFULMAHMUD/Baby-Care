package shiful.android.babycare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DoctorDetailsActivity extends AppCompatActivity {
    TextView txtName, txtCell, txtAddress,txtDescription;
    String getName, getCell, getAddress,getDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        txtName = findViewById(R.id.doc_name);
        txtCell = findViewById(R.id.doc_phone);
        txtAddress = findViewById(R.id.doc_address);
        txtDescription = findViewById(R.id.doc_description);


        getName = getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("cell");
        getAddress = getIntent().getExtras().getString("address");
        getDescription = getIntent().getExtras().getString("description");
/*
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contact Details");//for actionbar title*/


        txtName.setText(getName);
        txtCell.setText(getCell);
        txtAddress.setText(getAddress);
        txtDescription.setText(getDescription);

    }
}