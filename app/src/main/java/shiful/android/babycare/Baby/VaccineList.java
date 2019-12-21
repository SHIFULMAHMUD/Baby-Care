package shiful.android.babycare.Baby;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import shiful.android.babycare.Constant;
import shiful.android.babycare.Home.HomeActivity;
import shiful.android.babycare.R;
import shiful.android.babycare.User.UserProfile;

public class VaccineList extends AppCompatActivity {
    String getToDate;
    String getFromDate;
    String getBabyId,get_id=" ";
    private ProgressDialog loading;
    String mode,getCell,vac_id,id,id_v1,id_v2,id_v3,id_v4,id_v5,id_v6;
    TextView fromDate,toDate,fromDate1,toDate1,fromDate2,toDate2,fromDate3,toDate3,fromDate4,toDate4,fromDate5,toDate5,fromDate6,toDate6,fromDate7,toDate7,fromDate8,toDate8,fromDate9,toDate9,fromDate10,toDate10,fromDate11,toDate11,fromDate12,toDate12;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11,checkBox12,checkBox13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vaccine Schedule");

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

        fromDate=findViewById(R.id.fromdatetv);
        toDate=findViewById(R.id.todatetv);
        fromDate1=findViewById(R.id.fromdatetv1);
        toDate1=findViewById(R.id.todatetv1);
        fromDate2=findViewById(R.id.fromdatetv2);
        toDate2=findViewById(R.id.todatetv2);
        fromDate3=findViewById(R.id.fromdatetv3);
        toDate3=findViewById(R.id.todatetv3);
        fromDate4=findViewById(R.id.fromdatetv4);
        toDate4=findViewById(R.id.todatetv4);
        fromDate5=findViewById(R.id.fromdatetv5);
        toDate5=findViewById(R.id.todatetv5);
        fromDate6=findViewById(R.id.fromdatetv6);
        toDate6=findViewById(R.id.todatetv6);
        fromDate7=findViewById(R.id.fromdatetv7);
        toDate7=findViewById(R.id.todatetv7);
        fromDate8=findViewById(R.id.fromdatetv8);
        toDate8=findViewById(R.id.todatetv8);
        fromDate9=findViewById(R.id.fromdatetv9);
        toDate9=findViewById(R.id.todatetv9);
        fromDate10=findViewById(R.id.fromdatetv10);
        toDate10=findViewById(R.id.todatetv10);
        fromDate11=findViewById(R.id.fromdatetv11);
        toDate11=findViewById(R.id.todatetv11);
        fromDate12=findViewById(R.id.fromdatetv12);
        toDate12=findViewById(R.id.todatetv12);
        checkBox1=findViewById(R.id.chk_bcg);
        checkBox2=findViewById(R.id.chk_penta1);
        checkBox3=findViewById(R.id.chk_pcv1);
        checkBox4=findViewById(R.id.chk_opv1);
        checkBox5=findViewById(R.id.chk_penta2);
        checkBox6=findViewById(R.id.chk_pcv2);
        checkBox7=findViewById(R.id.chk_opv2);
        checkBox8=findViewById(R.id.chk_penta3);
        checkBox9=findViewById(R.id.chk_pcv3);
        checkBox10=findViewById(R.id.chk_opv3);
        checkBox11=findViewById(R.id.chk_opv4);
        checkBox12=findViewById(R.id.chk_mr);
        checkBox13=findViewById(R.id.chk_measles);

        getFromDate = getIntent().getExtras().getString("fromdate");
        getToDate = getIntent().getExtras().getString("todate");
        getBabyId = getIntent().getExtras().getString("babyid");
//Log
        Log.d("SP_ID",getBabyId);

        fromDate.setText(getFromDate);
        toDate.setText(getToDate);

        String sDate = getFromDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 42);
        String nextOne = dateFormat.format(calendar.getTime());
        fromDate1.setText(nextOne);

        String aDate = nextOne;
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date1 = null;
        try {
            date1 = dateFormat1.parse(aDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.add(Calendar.DATE, 7);
        String nextTwo = dateFormat1.format(calendar1.getTime());
        toDate1.setText(nextTwo);

        fromDate2.setText(nextOne);
        toDate2.setText(nextTwo);

        fromDate3.setText(nextOne);
        toDate3.setText(nextTwo);

        String bDate = nextTwo;
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date2 = null;
        try {
            date2 = dateFormat2.parse(bDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        calendar2.add(Calendar.DATE, 30);
        String nextThree = dateFormat2.format(calendar2.getTime());
        fromDate4.setText(nextThree);

        String mDate = nextThree;
        SimpleDateFormat dateFormat10 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date10 = null;
        try {
            date10 = dateFormat10.parse(mDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar10 = Calendar.getInstance();
        calendar10.setTime(date10);
        calendar10.add(Calendar.DATE, 7);
        String nextTen = dateFormat10.format(calendar10.getTime());
        toDate4.setText(nextTen);

        fromDate5.setText(nextThree);
        toDate5.setText(nextTen);

        fromDate6.setText(nextThree);
        toDate6.setText(nextTen);

        String cDate = nextTen;
        SimpleDateFormat dateFormat3 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date3 = null;
        try {
            date3 = dateFormat3.parse(cDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar3 = Calendar.getInstance();
        calendar3.setTime(date3);
        calendar3.add(Calendar.DATE, 30);
        String nextFour = dateFormat3.format(calendar3.getTime());
        fromDate7.setText(nextFour);

        String nDate = nextFour;
        SimpleDateFormat dateFormat11 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date11 = null;
        try {
            date11 = dateFormat11.parse(nDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar11 = Calendar.getInstance();
        calendar11.setTime(date11);
        calendar11.add(Calendar.DATE, 7);
        String nextEleven = dateFormat11.format(calendar11.getTime());
        toDate7.setText(nextEleven);

        fromDate8.setText(nextFour);
        toDate8.setText(nextEleven);

        fromDate9.setText(nextFour);
        toDate9.setText(nextEleven);

        String dDate = nextEleven;
        SimpleDateFormat dateFormat4 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date4 = null;
        try {
            date4 = dateFormat4.parse(dDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar4 = Calendar.getInstance();
        calendar4.setTime(date4);
        calendar4.add(Calendar.DATE, 30);
        String nextFive = dateFormat4.format(calendar4.getTime());
        fromDate10.setText(nextFive);
        toDate10.setText("As early as possible.");


        String eDate = getFromDate;
        SimpleDateFormat dateFormat5 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date5 = null;
        try {
            date5 = dateFormat5.parse(eDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar5 = Calendar.getInstance();
        calendar5.setTime(date5);
        calendar5.add(Calendar.DATE, 275);
        String nextSix = dateFormat5.format(calendar5.getTime());
        fromDate11.setText(nextSix);
        toDate11.setText("As early as possible.");

        String fDate = getFromDate;
        SimpleDateFormat dateFormat6 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date6 = null;
        try {
            date6 = dateFormat6.parse(fDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar6 = Calendar.getInstance();
        calendar6.setTime(date6);
        calendar6.add(Calendar.DATE, 455);
        String nextSeven = dateFormat6.format(calendar6.getTime());
        fromDate12.setText(nextSeven);
        toDate12.setText("As early as possible.");

        getData("");


            checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                    builder.setIcon(R.mipmap.ic_launcher)
                            .setMessage("Have you used this vaccine?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    // Perform Your Task Here--When Yes Is Pressed.

                                    if (id_v1.isEmpty())
                                    {
                                        id_v1="0";
                                    }
                                    SaveCheckbox(id_v1); //call SaveContact function
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
        /*checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Perform Your Task Here--When Yes Is Pressed.

                                    SaveCheckbox(id_v2); //call SaveContact function
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
        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Perform Your Task Here--When Yes Is Pressed.

                                    SaveCheckbox(id_v3); //call SaveContact function
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

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Perform Your Task Here--When Yes Is Pressed.

                                    SaveCheckbox(id_v4); //call SaveContact function
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
        checkBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Perform Your Task Here--When Yes Is Pressed.

                                    SaveCheckbox(id_v5); //call SaveContact function
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

        checkBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // Perform Your Task Here--When Yes Is Pressed.

                                    SaveCheckbox(id_v6); //call SaveContact function
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
        });*/
//        checkBox7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(v); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
//        checkBox8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
//        checkBox9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
//        checkBox10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
//        checkBox11.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
//        checkBox12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineList.this);
//                builder.setIcon(R.mipmap.ic_launcher)
//                        .setMessage("Have you used this vaccine?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                // Perform Your Task Here--When Yes Is Pressed.
//
//                                    SaveCheckbox(); //call SaveContact function
//                                    dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });
        }

    public void  SaveCheckbox(String id)
    {

        if (id.isEmpty())
        {
            get_id="0";
        }
        loading = new ProgressDialog(this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Adding");
        loading.setMessage("Please wait....");
        loading.show();

            if (checkBox1.isChecked())
            {
                mode="1";
                vac_id="101";

            }
            else
            {
                mode="0";
                vac_id="101";
            }
        /*if (checkBox2.isChecked())
        {
            mode="1";
            vac_id="102";

        }
        else
        {
            mode="0";
            vac_id="102";
        }
        if (checkBox3.isChecked())
        {
            mode="1";
            vac_id="103";

        }
        else
        {
            mode="0";
            vac_id="103";
        }
        if (checkBox4.isChecked())
        {
            mode="1";
            vac_id="104";

        }
        else
        {
            mode="0";
            vac_id="104";
        }
        if (checkBox5.isChecked())
        {
            mode="1";
            vac_id="105";

        }
        else
        {
            mode="0";
            vac_id="105";
        }
        if (checkBox6.isChecked())
        {
            mode="1";
            vac_id="106";

        }
        else
        {
            mode="0";
            vac_id="106";
        }
        if (checkBox7.isChecked())
        {
            mode="1";
            vac_id="107";

        }
        else
        {
            mode="0";
            vac_id="107";
        }
        if (checkBox8.isChecked())
        {
            mode="1";
            vac_id="108";

        }
        else
        {
            mode="0";
            vac_id="108";
        }
        if (checkBox9.isChecked())
        {
            mode="1";
            vac_id="109";

        }
        else
        {
            mode="0";
            vac_id="109";
        }
        if (checkBox10.isChecked())
        {
            mode="1";
            vac_id="110";

        }
        else
        {
            mode="0";
            vac_id="110";
        }
        if (checkBox11.isChecked())
        {
            mode="1";
            vac_id="111";

        }
        else
        {
            mode="0";
            vac_id="111";
        }
        if (checkBox12.isChecked())
        {
            mode="1";
            vac_id="112";

        }
        else
        {
            mode="0";
            vac_id="112";
        }*/

            String URL = Constant.ADD_CHECKBOX_URL;

            Log.d("SAVE_URL",URL);

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
                                Toast.makeText(VaccineList.this, " Vaccine Schedule Updated!", Toast.LENGTH_SHORT).show();

                            }


                            //If we are getting failure from server
                            else if (response.equals("failure")) {
                                loading.dismiss();
                                //Intent intent = new Intent(AddContactsActivity.this, HomeActivity.class);
                                Toast.makeText(VaccineList.this, " Vaccine Schedule failed to update!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {
                                loading.dismiss();
                                Toast.makeText(VaccineList.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want
                            Toast.makeText(VaccineList.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request
                    params.put(Constant.KEY_ID,get_id);
                    params.put(Constant.KEY_VACCINE_ID,vac_id);
                    params.put(Constant.KEY_MODE,mode);
                    params.put(Constant.KEY_BABY_ID,getBabyId);
                    params.put(Constant.KEY_USER_CELL,getCell);

                    Log.d("mode", ""+mode + " ID="+get_id);
                    Log.d("id", ""+get_id);

                    //returning parameter
                    return params;
                }
            };

            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(VaccineList.this);
            requestQueue.add(stringRequest);
        }

    private void getData(String text) {

        loading=new ProgressDialog(this);
        loading.setMessage("Loading. . . ");
        loading.show();

        String URL = Constant.VIEW_CHECKBOX_URL+getCell+"&baby_id="+getBabyId;
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

                        Toast.makeText(VaccineList.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(VaccineList.this);
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
                Toast.makeText(VaccineList.this, "No Vaccine Taken!", Toast.LENGTH_SHORT).show();
            }
            else {

                for (int i = 0; i < result.length(); i++) {

                    JSONObject jo = result.getJSONObject(i);
                    id = jo.getString(Constant.KEY_ID);
                    String vaccine_id = jo.getString(Constant.KEY_VACCINE_ID);
                    String mode = jo.getString(Constant.KEY_MODE);
                    String child_id = jo.getString(Constant.KEY_BABY_ID);

                    if (vaccine_id.equals("101") && mode.equals("1") && child_id.equals(getBabyId)) {
                        checkBox1.setChecked(true);
                        id_v1 = id;
                    } else {
                        checkBox1.setChecked(false);
                    }
          /*          if (vaccine_id.equals("102") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox2.setChecked(true);
                        id_v2=id;
                    }else {
                        checkBox2.setChecked(false);
                    }
                    if (vaccine_id.equals("103") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox3.setChecked(true);
                    }else {
                        checkBox3.setChecked(false);
                    }
                    if (vaccine_id.equals("104") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox4.setChecked(true);
                    }else {
                        checkBox4.setChecked(false);
                    }
                    if (vaccine_id.equals("105") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox5.setChecked(true);
                    }else {
                        checkBox5.setChecked(false);
                    }
                    if (vaccine_id.equals("106") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox6.setChecked(true);
                    }else {
                        checkBox6.setChecked(false);
                    }
                    if (vaccine_id.equals("107") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox7.setChecked(true);
                    }else {
                        checkBox7.setChecked(false);
                    }
                    if (vaccine_id.equals("108") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox8.setChecked(true);
                    }else {
                        checkBox8.setChecked(false);
                    }
                    if (vaccine_id.equals("109") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox9.setChecked(true);
                    }else {
                        checkBox9.setChecked(false);
                    }
                    if (vaccine_id.equals("110") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox10.setChecked(true);
                    }else {
                        checkBox10.setChecked(false);
                    }
                    if (vaccine_id.equals("111") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox11.setChecked(true);
                    }else {
                        checkBox11.setChecked(false);
                    }
                    if (vaccine_id.equals("112") && mode.equals("1") && child_id.equals(getBabyId)){
                        checkBox12.setChecked(true);
                    }else {
                        checkBox12.setChecked(false);
                    }
                    }*/
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}