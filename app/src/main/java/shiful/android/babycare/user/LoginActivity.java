
package shiful.android.babycare.user;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.util.Log;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.CompoundButton;
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
        import shiful.android.babycare.home.HomeActivity;
        import shiful.android.babycare.R;

public class LoginActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener{

    //EditText object declaration
    EditText etxtCell,etxtPassword;
    //Button object declaration
    Button btnSignUp,btnLogin;
    //ProgressDialog object declaration
    private ProgressDialog loading;
    private static long back_pressed;
    private static final int TIME_DELAY = 2000;

    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_USERCELL = "usercell";
    private static final String KEY_PASS = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BABY CARE");

        //Link up to java object to xml Button id
        btnSignUp = (Button) findViewById(R.id.btn_gotoRegister);
        btnLogin = (Button) findViewById(R.id.btn_signIn);
        rem_userpass = findViewById(R.id.ch_rememberme);
        //Link up to java object to xml EditText id
        etxtCell = (EditText) findViewById(R.id.edittext_phone);
        etxtPassword = (EditText) findViewById(R.id.edittext_password);

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        etxtCell.setText(sharedPreferences.getString(KEY_USERCELL, ""));
        etxtPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        etxtCell.addTextChangedListener(this);
        etxtPassword.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);


        //Click listener in LoginActivity Button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Call AppSingleton function
                AppSingleton();

            }
        });

        //Click listener in Signup Button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    //LoginActivity function
    private void AppSingleton() {
        //Getting values from edit texts
        final String cell = etxtCell.getText().toString().trim();
        final String password = etxtPassword.getText().toString().trim();


        //Checking usercell field/validation
        if (cell.isEmpty()) {
            etxtCell.setError("Please enter cell !");
            requestFocus(etxtCell);
        }



        //Checking password field/validation
        else if (password.isEmpty()) {
            etxtPassword.setError("Password can't be empty!");
            requestFocus(etxtPassword);
        }

        //showing progress dialog

        else {

            loading = new ProgressDialog(this);
            // loading.setIcon(R.drawable.wait_icon);
            loading.setTitle("Logging in");
            loading.setMessage("Please wait....");
            loading.show();

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Response",""+response);
                            //If we are getting success from server
                            if (response.equals("success")) {
                                //Creating a shared preference

                                SharedPreferences sp = LoginActivity.this.getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                //Creating editor to store values to shared preferences
                                SharedPreferences.Editor editor = sp.edit();
                                //Adding values to editor
                                editor.putString(Constant.CELL_SHARED_PREF, cell);

                                //Saving values to editor
                                editor.commit();

                                loading.dismiss();
                                //Starting Home activity
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                Toasty.success(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                finish();
                            }
                            else if(response.equals("failure")) {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toasty.error(LoginActivity.this, "Invalid Login", Toast.LENGTH_LONG).show();
                                loading.dismiss();
                            }

                            else {
                                //If the server response is not success
                                //Displaying an error message on toast
                                Toasty.error(LoginActivity.this, "Invalid user cell or password", Toast.LENGTH_LONG).show();
                                loading.dismiss();
                            }
                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(LoginActivity.this, "There is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_PASSWORD, password);

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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }

    private void managePrefs(){
        if(rem_userpass.isChecked()){
            editor.putString(KEY_USERCELL, etxtCell.getText().toString().trim());
            editor.putString(KEY_PASS, etxtPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);//editor.putString(KEY_PASS,"");
            editor.remove(KEY_USERCELL);//editor.putString(KEY_USERCELL, "");
            editor.apply();
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
