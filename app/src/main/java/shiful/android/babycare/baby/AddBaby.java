package shiful.android.babycare.baby;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import es.dmoral.toasty.Toasty;
import shiful.android.babycare.Constant;
import shiful.android.babycare.R;

public class AddBaby extends AppCompatActivity  implements
        AdapterView.OnItemSelectedListener {
    final Calendar myCalendar = Calendar.getInstance();
    String bgvalue;
    Button btnAdd;
    EditText etxtName,etxtGender,etxtDob,etxtBp;
    private ProgressDialog loading;
    String getCell;
    String[] bloodgroup = { "O negative", "O positive","A negative","A positive","B negative","B positive","AB negative","AB positive"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BABY CARE");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        Spinner spinner = (Spinner) findViewById(R.id.spinner_bg);
        spinner.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the gender list
        ArrayAdapter bb = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bloodgroup);
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(bb);

        btnAdd=(Button)findViewById(R.id.add_btn);
        etxtName=(EditText)findViewById(R.id.et_babyname);
        etxtDob=(EditText)findViewById(R.id.et_dob);
        etxtBp=(EditText)findViewById(R.id.et_bp);
        etxtGender=(EditText)findViewById(R.id.et_gender);
        //For choosing account type and open alert dialog
        etxtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(AddBaby.this);
                builder.setTitle("SELECT GENDER");
                builder.setIcon(R.drawable.gender);


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                etxtGender.setText(genderList[position]);
                                break;

                            case 1:
                                etxtGender.setText(genderList[position]);
                                break;


                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog accountTypeDialog = builder.create();

                accountTypeDialog.show();
            }

        });

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

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


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etxtDob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddBaby.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etxtDob.setText(sdf.format(myCalendar.getTime()));
    }
    //Save contact method
    public void  SaveContact()
    {

        final String baby_name=etxtName.getText().toString().trim();
        final String baby_gender=etxtGender.getText().toString().trim();
        final String blood_group=bgvalue;
        final String date_of_birth=etxtDob.getText().toString().trim();
        final String birth_place=etxtBp.getText().toString().trim();

//Checking  field/validation
        if (baby_name.isEmpty()) {
            etxtName.setError("Please enter baby name !");
            requestFocus(etxtName);
        }
        else if (baby_gender.isEmpty()) {
            etxtGender.setError("Please enter gender !");
            requestFocus(etxtGender);
            Toasty.error(this, "Gender can't be empty", Toast.LENGTH_SHORT).show();

        }
        else if (blood_group.isEmpty()) {

            Toasty.error(this, "Blood Group can't be empty", Toast.LENGTH_SHORT).show();

        }
        else if (date_of_birth.isEmpty()) {

            etxtDob.setError("Please enter date of birth !");
            requestFocus(etxtDob);
            Toasty.error(this, "Date of birth can't be empty", Toast.LENGTH_SHORT).show();

        }
        else if ((birth_place.isEmpty())){

            etxtBp.setError("Please enter birth place address !");
            requestFocus(etxtBp);

        } else {
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
                                Toasty.success(AddBaby.this, " Successfully Added!", Toast.LENGTH_LONG).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                //Intent intent = new Intent(AddContactsActivity.this, HomeActivity.class);
                                Toasty.error(AddBaby.this, " Adding Failed!", Toast.LENGTH_LONG).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(AddBaby.this, "Network Error", Toast.LENGTH_LONG).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(AddBaby.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
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
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        bgvalue=bloodgroup[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    //for request focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
