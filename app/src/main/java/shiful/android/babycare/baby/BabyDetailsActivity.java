package shiful.android.babycare.baby;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import shiful.android.babycare.R;

public class BabyDetailsActivity extends AppCompatActivity {
    TextView txtName, txtGender, txtBg,txtDob,txtBp;
    String getId,getName, getGender, getBg,getDob,getBp;
    Button vacList;
    ImageView genderiv;
    CheckBox checkBox_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BABY DETAILS");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        genderiv=findViewById(R.id.gender_imageview);
        txtName = findViewById(R.id.baby_name);
        txtGender = findViewById(R.id.baby_gender);
        txtBg = findViewById(R.id.blood_group);
        txtDob = findViewById(R.id.date_of_birth);
        txtBp = findViewById(R.id.birth_place);

        /*checkBox_notification=findViewById(R.id.ch_notifyme);
        checkBox_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        getId = getIntent().getExtras().getString("id");
        getName = getIntent().getExtras().getString("name");
        getGender = getIntent().getExtras().getString("gender");
        getBg = getIntent().getExtras().getString("bloodgroup");
        getDob = getIntent().getExtras().getString("dateofbirth");
        getBp = getIntent().getExtras().getString("birthplace");

        if (getGender.equals("Male")) {
            genderiv.setBackgroundResource(R.drawable.sexmale);
        }else{
            genderiv.setBackgroundResource(R.drawable.sexfemale);
        }

        txtName.setText(getName);
        txtGender.setText(getGender);
        txtBg.setText(getBg);
        txtDob.setText(getDob);
        txtBp.setText(getBp);

        vacList=findViewById(R.id.vacListBtn);
        vacList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDate = getDob;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date = null;
                try {
                    date = dateFormat.parse(sDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 40);
                String nextDate = dateFormat.format(calendar.getTime());

                Intent intent=new Intent(BabyDetailsActivity.this,VaccineSchedule.class);
                intent.putExtra("fromdate",getDob);
                intent.putExtra("todate",nextDate);
                intent.putExtra("babyid",getId);
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
