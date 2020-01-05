package shiful.android.babycare.vaccine;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import shiful.android.babycare.R;


public class VaccineDetailsActivity extends AppCompatActivity {
    TextView txtVaccine, txtDisease, txtDoseno,txtDoseinterval,txtStarttime,txtRoute;
    String getVaccine, getDiseasename, getDoseno,getDoseinterval,getStarttime,getRoute;
    Button reqVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VACCINE DETAILS");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        txtVaccine = findViewById(R.id.vac_name);
        txtDisease = findViewById(R.id.disease_name);
        txtDoseno = findViewById(R.id.no_of_dose);
        txtDoseinterval = findViewById(R.id.interval);
        txtStarttime = findViewById(R.id.starting_time);
        txtRoute = findViewById(R.id.route);
        reqVaccine=findViewById(R.id.btn_req);

        getVaccine = getIntent().getExtras().getString("name");
        getDiseasename = getIntent().getExtras().getString("disease");
        getDoseno = getIntent().getExtras().getString("doseno");
        getDoseinterval = getIntent().getExtras().getString("doseinterval");
        getStarttime = getIntent().getExtras().getString("starttime");
        getRoute = getIntent().getExtras().getString("route");

        txtVaccine.setText(getVaccine);
        txtDisease.setText(getDiseasename);
        txtDoseno.setText(getDoseno);
        txtDoseinterval.setText(getDoseinterval);
        txtStarttime.setText(getStarttime);
        txtRoute.setText(getRoute);

        reqVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VaccineDetailsActivity.this,RequestVaccine.class);
                intent.putExtra("vaccine_name",getVaccine);
                startActivity(intent);
            }
        });
    }
    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}