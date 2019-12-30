package shiful.android.babycare.User;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
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

        import es.dmoral.toasty.Toasty;
        import shiful.android.babycare.Constant;
        import shiful.android.babycare.R;

public class RegisterActivity extends AppCompatActivity {


    //Declaring Java EditText objects
    EditText etxtName,etxtCell,etxtPassword,etxtEmail,etxtGender;
    //Declaring Java Button objects
    Button btnSignUp,btnSignIn;
    ProgressDialog loading;

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
        else if (cell.isEmpty()) {

            etxtCell.setError("Please enter cell !");
            requestFocus(etxtCell);

        }
        else if (email.isEmpty()) {

            etxtEmail.setError("Please enter email !");
            requestFocus(etxtEmail);

        }
        else if (gender.isEmpty()) {

            etxtGender.setError("Please enter gender !");
            requestFocus(etxtGender);

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
}