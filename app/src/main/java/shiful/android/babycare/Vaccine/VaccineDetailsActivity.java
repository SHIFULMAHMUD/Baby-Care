package shiful.android.babycare.Vaccine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import shiful.android.babycare.R;


public class VaccineDetailsActivity extends AppCompatActivity {
    TextView txtVaccine, txtDisease, txtDoseno,txtDoseinterval,txtStarttime,txtRoute;
    String getVaccine, getDiseasename, getDoseno,getDoseinterval,getStarttime,getRoute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);

        txtVaccine = findViewById(R.id.vac_name);
        txtDisease = findViewById(R.id.disease_name);
        txtDoseno = findViewById(R.id.no_of_dose);
        txtDoseinterval = findViewById(R.id.interval);
        txtStarttime = findViewById(R.id.starting_time);
        txtRoute = findViewById(R.id.route);


        getVaccine = getIntent().getExtras().getString("name");
        getDiseasename = getIntent().getExtras().getString("disease");
        getDoseno = getIntent().getExtras().getString("doseno");
        getDoseinterval = getIntent().getExtras().getString("doseinterval");
        getStarttime = getIntent().getExtras().getString("starttime");
        getRoute = getIntent().getExtras().getString("route");
/*
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contact Details");//for actionbar title*/


        txtVaccine.setText(getVaccine);
        txtDisease.setText(getDiseasename);
        txtDoseno.setText(getDoseno);
        txtDoseinterval.setText(getDoseinterval);
        txtStarttime.setText(getStarttime);
        txtRoute.setText(getRoute);

    }
}