package shiful.android.babycare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AddBaby extends AppCompatActivity {
    Button btnAdd;
    EditText etxtName,etxtGender,etxtBloodgroup,etxtDob,etxtBp;
    private ProgressDialog loading;
    String getCell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);

        btnAdd=(Button)findViewById(R.id.add_btn);
        etxtName=(EditText)findViewById(R.id.et_babyname);
        etxtGender=(EditText)findViewById(R.id.et_gender);
        etxtBloodgroup=(EditText)findViewById(R.id.et_bg);
        etxtDob=(EditText)findViewById(R.id.et_dob);
        etxtBp=(EditText)findViewById(R.id.et_bp);

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

/*
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Add Contacts");//for actionbar title
*/

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AddBaby.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to add baby?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                // Perform Your Task Here--When Yes Is Pressed.
                                SaveContact(); //call SaveContact function
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();


            }
        });

    }
    //Save contact method
    public void  SaveContact()
    {

        final String baby_name=etxtName.getText().toString();
        final String baby_gender=etxtGender.getText().toString();
        final String blood_group=etxtBloodgroup.getText().toString();
        final String date_of_birth=etxtDob.getText().toString();
        final String birth_place=etxtBp.getText().toString();


        if (baby_name.isEmpty())
        {
            Toast.makeText(this, "Name Can't Be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (baby_gender.isEmpty())
        {
            Toast.makeText(this, "Gender Can't Empty", Toast.LENGTH_SHORT).show();
        }

        else if(blood_group.isEmpty())
        {
            Toast.makeText(this, "Blood Group Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(date_of_birth.isEmpty())
        {
            Toast.makeText(this, "Date of Birth Can't Empty", Toast.LENGTH_SHORT).show();
        }
        else if(birth_place.isEmpty())
        {
            Toast.makeText(this, "Birth Place Can't Empty", Toast.LENGTH_SHORT).show();
        }

        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Adding");
            loading.setMessage("Please wait....");
            loading.show();

            String URL = Constant.ADD_BABY_URL;


            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for track response in logcat
                            Log.d("RESPONSE", response);
                            // Log.d("RESPONSE", userCell);


                            //If we are getting success from server
                            if (response.equals("success")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(AddBaby.this, ViewBaby.class);
                                Toast.makeText(AddBaby.this, " Successfully Added!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                //Intent intent = new Intent(AddContactsActivity.this, HomeActivity.class);
                                Toast.makeText(AddBaby.this, " Adding failed!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toast.makeText(AddBaby.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toast.makeText(AddBaby.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_BABY_NAME, baby_name);
                    params.put(Constant.KEY_BABY_GENDER, baby_gender);
                    params.put(Constant.KEY_BLOODGROUP, blood_group);
                    params.put(Constant.KEY_DOB, date_of_birth);
                    params.put(Constant.KEY_BP, birth_place);
                    params.put(Constant.KEY_USER_CELL, getCell);

                    // Log.d("ID", getID);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(AddBaby.this);
            requestQueue.add(stringRequest);
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
