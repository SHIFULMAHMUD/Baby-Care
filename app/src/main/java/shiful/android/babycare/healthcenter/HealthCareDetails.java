package shiful.android.babycare.healthcenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
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
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        txtName = findViewById(R.id.hc_name);
        txtCell = findViewById(R.id.hc_phone);
        txtAddress = findViewById(R.id.hc_address);
        txtWebsite = findViewById(R.id.hc_website);


        getName = getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("phone");
        getAddress = getIntent().getExtras().getString("location");
        getWebsite = getIntent().getExtras().getString("website");

        txtName.setText(getName);
        txtCell.setText(getCell);
        txtAddress.setText(getAddress);
        txtWebsite.setText(Html.fromHtml(getWebsite));
        txtWebsite.setMovementMethod(LinkMovementMethod.getInstance());

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