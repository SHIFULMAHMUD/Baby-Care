package shiful.android.babycare.HealthCenter;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import shiful.android.babycare.R;


public class HealthCareDetails extends AppCompatActivity {
    TextView txtName, txtCell, txtAddress,txtWebsite;
    String getName, getCell, getAddress,getWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HEALTH CENTER DETAILS");

        txtName = findViewById(R.id.hc_name);
        txtCell = findViewById(R.id.hc_phone);
        txtAddress = findViewById(R.id.hc_address);
        txtWebsite = findViewById(R.id.hc_website);


        getName = getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("phone");
        getAddress = getIntent().getExtras().getString("location");
        getWebsite = getIntent().getExtras().getString("website");
/*
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contact Details");//for actionbar title*/


        txtName.setText(getName);
        txtCell.setText(getCell);
        txtAddress.setText(getAddress);
        txtWebsite.setText(Html.fromHtml(getWebsite));
        txtWebsite.setMovementMethod(LinkMovementMethod.getInstance());

    }
}