package shiful.android.babycare.user;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import es.dmoral.toasty.Toasty;
import shiful.android.babycare.Constant;
import shiful.android.babycare.R;

public class RegisterActivity extends AppCompatActivity {


    //Declaring Java EditText objects
    EditText etxtName,etxtCell,etxtPassword,etxtEmail,etxtGender;
    //Declaring Java Button objects
    Button btnSignUp,btnSignIn;
    ProgressDialog loading;
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BABY CARE");

        //Assigning java object to xml edit text fields
        etxtName=(EditText)findViewById(R.id.et_username);
        etxtCell=(EditText)findViewById(R.id.et_phone);
        etxtEmail=(EditText)findViewById(R.id.et_email);
        etxtGender=(EditText)findViewById(R.id.et_gender);
        etxtPassword=(EditText)findViewById(R.id.et_password);

        btnSignIn=findViewById(R.id.bt_gotoLogin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //Assigning java object to xml edit text fields
        btnSignUp=(Button)findViewById(R.id.bt_Register);

        //set click listener in Sign up button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sign_up();

            }
        });


        //For choosing account type and open alert dialog
        etxtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
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

    }



    private void sign_up() {

        //Getting values from edit texts
        final String name = etxtName.getText().toString().trim();
        final String cell = etxtCell.getText().toString().trim();
        final String email = etxtEmail.getText().toString().trim();
        final String gender = etxtGender.getText().toString().trim();
        final String password = etxtPassword.getText().toString().trim();


        //Checking  field/validation
        if (name.isEmpty()) {
            etxtName.setError("Please enter name !");
            requestFocus(etxtName);
        }
        else if (cell.length()!=11) {

            etxtCell.setError("Please enter valid phone number !");
            requestFocus(etxtCell);

        }

        else if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {

            etxtEmail.setError("Please enter valid email !");
            requestFocus(etxtEmail);

        }
        else if (gender.isEmpty()) {

            etxtGender.setError("Please enter gender !");
            requestFocus(etxtGender);
            Toasty.error(this, "Gender can't be empty", Toast.LENGTH_SHORT).show();

        }
        else if (password.isEmpty()) {

            etxtPassword.setError("Please enter password !");
            requestFocus(etxtPassword);

        }

        else
        {
            loading = new ProgressDialog(this);
            loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Sign up");
            loading.setMessage("Please wait....");
            loading.show();


            StringRequest stringRequest=new StringRequest(Request.Method.POST, Constant.SIGNUP_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //for track response in logcat
                    Log.d("RESPONSE", response);

                    if (response.equals("success")) {
                        loading.dismiss();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        Toasty.success(RegisterActivity.this, "Sign up successful", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    } else if (response.equals("exists")) {

                        Toasty.warning(RegisterActivity.this, "User already exists!", Toast.LENGTH_LONG).show();
                        loading.dismiss();

                    }

                    else if (response.equals("failure")) {

                        Toasty.error(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_LONG).show();
                        loading.dismiss();

                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toasty.error(RegisterActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_EMAIL, email);
                    params.put(Constant.KEY_GENDER, gender);
                    params.put(Constant.KEY_PASSWORD, password);


                    Log.d("info",""+name+" "+cell);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }



    //for request focus
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toasty.info(getBaseContext(), R.string.press_once_again_to_exit,
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}