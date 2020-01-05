package shiful.android.babycare.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import shiful.android.babycare.baby.AddBaby;
import shiful.android.babycare.baby.VaccineSchedule;
import shiful.android.babycare.baby.ViewBaby;
import shiful.android.babycare.doctor.DoctorActivity;
import shiful.android.babycare.healthcenter.HealthCenterActivity;
import shiful.android.babycare.maps.MapsActivity;
import shiful.android.babycare.R;
import shiful.android.babycare.user.LoginActivity;
import shiful.android.babycare.user.UserProfile;
import shiful.android.babycare.vaccine.RequestVaccine;
import shiful.android.babycare.vaccine.VaccineActivity;
import shiful.android.babycare.vaccine.ViewRequest;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView read,user,showbaby,showdoctor,showhealthcenter,showvaccine,viewreq,notification;
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Press the button and Call Method => [ ReadPDF ]
        read=findViewById(R.id.b7);
        read.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, PdfActivity.class);
                startActivity(intent);
            }
        });

        notification=findViewById(R.id.b6);
        notification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        user=findViewById(R.id.b1);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, UserProfile.class);
                startActivity(intent);
            }
        });
        showbaby=findViewById(R.id.b2);
        showbaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, ViewBaby.class);
                startActivity(intent);
            }
        });
        showdoctor=findViewById(R.id.b3);
        showdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, DoctorActivity.class);
                startActivity(intent);
            }
        });
        showhealthcenter=findViewById(R.id.b5);
        showhealthcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, HealthCenterActivity.class);
                startActivity(intent);
            }
        });
        showvaccine=findViewById(R.id.b4);
        showvaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, VaccineActivity.class);
                startActivity(intent);
            }
        });
        viewreq=findViewById(R.id.b8);
        viewreq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, ViewRequest.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toasty.info(getBaseContext(), R.string.press_once_again_to_exit,
                    Toast.LENGTH_SHORT).show();
        }
            back_pressed = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent=new Intent(HomeActivity.this, UserProfile.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_add_baby) {
            Intent intent=new Intent(HomeActivity.this, ViewBaby.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_order_vaccine) {
            Intent intent=new Intent(HomeActivity.this, VaccineActivity.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_nearby_hospital) {
            Intent intent=new Intent(HomeActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_guide) {
            Intent intent=new Intent(HomeActivity.this, PdfActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_sign_out) {
            Intent intent=new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
