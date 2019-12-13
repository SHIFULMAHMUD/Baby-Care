package shiful.android.babycare.Baby;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import shiful.android.babycare.R;

public class BabyDetailsActivity extends AppCompatActivity {
    TextView txtName, txtGender, txtBg,txtDob,txtBp;
    String getName, getGender, getBg,getDob,getBp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_details);

        txtName = findViewById(R.id.baby_name);
        txtGender = findViewById(R.id.baby_gender);
        txtBg = findViewById(R.id.blood_group);
        txtDob = findViewById(R.id.date_of_birth);
        txtBp = findViewById(R.id.birth_place);


        getName = getIntent().getExtras().getString("name");
        getGender = getIntent().getExtras().getString("gender");
        getBg = getIntent().getExtras().getString("bloodgroup");
        getDob = getIntent().getExtras().getString("dateofbirth");
        getBp = getIntent().getExtras().getString("birthplace");
/*
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contact Details");//for actionbar title*/


        txtName.setText(getName);
        txtGender.setText(getGender);
        txtBg.setText(getBg);
        txtDob.setText(getDob);
        txtBp.setText(getBp);

    }
}