package shiful.android.babycare.vaccine;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import es.dmoral.toasty.Toasty;
import shiful.android.babycare.Constant;
import shiful.android.babycare.R;

public class RequestVaccine extends AppCompatActivity {
    Button btnSubmit;
    EditText etxtchildName,etxtParentName,etxtAddress;
    TextView vacname,parentcell;
    private ProgressDialog loading;
    String getVac,getCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_vaccine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ORDER VACCINE");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        vacname=findViewById(R.id.tv_vaccinname);
        parentcell=findViewById(R.id.tv_cell);
        btnSubmit=(Button)findViewById(R.id.btn_submit);
        etxtParentName=(EditText)findViewById(R.id.et_parentname);
        etxtchildName=(EditText)findViewById(R.id.et_childname);
        etxtAddress=(EditText)findViewById(R.id.et_address);

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        parentcell.setText(getCell);

        getVac = getIntent().getExtras().getString("vaccine_name");
        vacname.setText(getVac);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(shiful.android.babycare.vaccine.RequestVaccine.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Want to request vaccine?")
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
        final String vaccine_name=getVac;
        final String baby_name=etxtchildName.getText().toString();
        final String parent_name=etxtParentName.getText().toString();
        final String parent_cell=getCell;
        final String parent_address=etxtAddress.getText().toString();

        if (baby_name.isEmpty())
        {
            etxtchildName.setError("Please enter baby name !");
            requestFocus(etxtchildName);
        }
        else if (parent_name.isEmpty())
        {
            etxtParentName.setError("Please enter parent name !");
            requestFocus(etxtParentName);
        }
        else if(parent_address.isEmpty())
        {
            etxtAddress.setError("Please enter address !");
            requestFocus(etxtAddress);
        }

        else {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Adding");
            loading.setMessage("Please wait....");
            loading.show();

            String URL = Constant.REQUEST_VACCINE_URL;


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
                                Toasty.success(RequestVaccine.this, "Successfully Requested!", Toast.LENGTH_LONG).show();

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                //Intent intent = new Intent(AddContactsActivity.this, HomeActivity.class);
                                Toasty.error(RequestVaccine.this, "Request Failed!", Toast.LENGTH_LONG).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(RequestVaccine.this, "Network Error", Toast.LENGTH_LONG).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            Toasty.error(shiful.android.babycare.vaccine.RequestVaccine.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Constant.KEY_VACCIN_NAME, vaccine_name);
                    params.put(Constant.KEY_CHILD_NAME, baby_name);
                    params.put(Constant.KEY_GUARDIAN_NAME, parent_name);
                    params.put(Constant.KEY_GUARDIAN_CELL, parent_cell);
                    params.put(Constant.KEY_GUARDIAN_ADDRESS, parent_address);

                    // Log.d("ID", getID);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(shiful.android.babycare.vaccine.RequestVaccine.this);
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
    //for request focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
