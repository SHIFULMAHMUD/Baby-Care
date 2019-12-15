package shiful.android.babycare.User;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shiful.android.babycare.Constant;
import shiful.android.babycare.Home.HomeActivity;
import shiful.android.babycare.R;

public class UserProfile extends AppCompatActivity {
    TextView nametv, emailtv, celltv, gendertv;
    private ProgressDialog loading;
    Button back;
    String getCell;
    int MAX_SIZE=999;

    public String userName[]=new String[MAX_SIZE];
    public String userCell[]=new String[MAX_SIZE];
    public String userEmail[]=new String[MAX_SIZE];
    public String userGender[]=new String[MAX_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("User Profile");
        nametv=findViewById(R.id.username_tv);
        emailtv=findViewById(R.id.usermail_tv);
        celltv=findViewById(R.id.usercell_tv);
        gendertv=findViewById(R.id.usergender_tv);
        back=findViewById(R.id.backbtn);

        /*getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contacts");*/

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

        //Log
        Log.d("SP_CELL",getCell);


        //call function to get data
        getData("");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserProfile.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getData(String text) {


        //for showing progress dialog
        loading = new ProgressDialog(UserProfile.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.USER_VIEW_URL+getCell;
        Log.d("SP_URL",URL);
        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toast.makeText(UserProfile.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(UserProfile.this);
        requestQueue.add(stringRequest);
    }



    private void showJSON(String response) {

        //Create json object for receiving json data
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


            if (result.length()==0)
            {
                Toast.makeText(UserProfile.this, "No Data Available!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(UserProfile.this, HomeActivity.class);

                startActivity(intent);
                //imgNoData.setImageResource(R.drawable.nodata);
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String name = jo.getString(Constant.KEY_NAME);
                    String cell = jo.getString(Constant.KEY_CELL);
                    String email = jo.getString(Constant.KEY_EMAIL);
                    String gender = jo.getString(Constant.KEY_GENDER);

                    //insert data into array for put extra

                    userName[i] = name;
                    userCell[i] = cell;
                    userEmail[i] = email;
                    userGender[i] = gender;

                    nametv.setText(name);
                    celltv.setText(cell);
                    emailtv.setText(email);
                    gendertv.setText(gender);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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