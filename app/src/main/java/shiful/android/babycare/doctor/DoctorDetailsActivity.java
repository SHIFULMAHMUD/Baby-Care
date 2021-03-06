package shiful.android.babycare.doctor;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import shiful.android.babycare.R;

public class DoctorDetailsActivity extends AppCompatActivity {
    TextView txtName, txtCell, txtAddress,txtDescription;
    String getName, getCell, getAddress,getDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CHILD SPECIALIST");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        txtName = findViewById(R.id.doc_name);
        txtCell = findViewById(R.id.doc_phone);
        txtAddress = findViewById(R.id.doc_address);
        txtDescription = findViewById(R.id.doc_description);


        getName = getIntent().getExtras().getString("name");
        getCell = getIntent().getExtras().getString("cell");
        getAddress = getIntent().getExtras().getString("address");
        getDescription = getIntent().getExtras().getString("description");

        txtName.setText(getName);
        txtCell.setText(getCell);
        txtAddress.setText(getAddress);
        txtDescription.setText(getDescription);

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