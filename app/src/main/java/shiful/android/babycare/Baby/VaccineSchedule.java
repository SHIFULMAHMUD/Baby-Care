package shiful.android.babycare.Baby;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import shiful.android.babycare.Constant;
import shiful.android.babycare.R;
import shiful.android.babycare.Vac;
import shiful.android.babycare.VaccineAdapter;

public class VaccineSchedule extends AppCompatActivity {
    ListView simpleList;
    private VaccineAdapter mAdapter;
    String getToDate,getFromDate,getBabyId;
    private ProgressDialog loading;
    String mode,getCell,vac_id;
    String status1,status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_schedule);

        simpleList = (ListView) findViewById(R.id.simpleListView);
        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

        getFromDate = getIntent().getExtras().getString("fromdate");
        getToDate = getIntent().getExtras().getString("todate");
        getBabyId = getIntent().getExtras().getString("babyid");
//Log
        Log.d("SP_ID",getBabyId);

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

        getData("");
        String status="";
        final ArrayList<Vac> vaccinesList = new ArrayList<>();
        vaccinesList.add(new Vac("BCG", "" , getFromDate,getToDate,status1));
        vaccinesList.add(new Vac("Pentavalent", "First Dose" , nextOne,nextTwo,status2));
        vaccinesList.add(new Vac("PCV", "First Dose" , nextOne,nextTwo,status));
        vaccinesList.add(new Vac("OPV", "First Dose" , nextOne,nextTwo,status));
        vaccinesList.add(new Vac("Pentavalent", "Second Dose" , nextThree,nextTen,status));
        vaccinesList.add(new Vac("PCV", "Second Dose" , nextThree,nextTen,status));
        vaccinesList.add(new Vac("OPV", "Second Dose" , nextThree,nextTen,status));
        vaccinesList.add(new Vac("Pentavalent", "Third Dose" , nextFour,nextEleven,status));
        vaccinesList.add(new Vac("PCV", "Third Dose" , nextFour,nextEleven,status));
        vaccinesList.add(new Vac("OPV", "Third Dose" , nextFour,nextEleven,status));
        vaccinesList.add(new Vac("OPV", "Fourth Dose" , nextFive,"As early as possible.",status));
        vaccinesList.add(new Vac("MR", "" , nextSix,"As early as possible.",status));
        vaccinesList.add(new Vac("Measles", "" , nextSeven,"As early as possible.",status));
        mAdapter = new VaccineAdapter(this,vaccinesList);
        simpleList.setAdapter(mAdapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(VaccineSchedule.this);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setMessage("Have you used this vaccine?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                    SaveCheckbox();
                                    dialog.cancel();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();
                mAdapter.notifyDataSetChanged();
            }
        });


            }

    public void  SaveCheckbox()
    {
        loading = new ProgressDialog(this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Adding");
        loading.setMessage("Please wait....");
        loading.show();

            mode="1";
            vac_id="101";

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
                            Toast.makeText(VaccineSchedule.this, " Vaccine Schedule Updated!", Toast.LENGTH_SHORT).show();

                        }


                        //If we are getting failure from server
                        else if (response.equals("failure")) {
                            loading.dismiss();
                            //Intent intent = new Intent(AddContactsActivity.this, HomeActivity.class);
                            Toast.makeText(VaccineSchedule.this, " Vaccine Schedule failed to update!", Toast.LENGTH_SHORT).show();
                            //startActivity(intent);

                        } else {
                            loading.dismiss();
                            Toast.makeText(VaccineSchedule.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(VaccineSchedule.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Constant.KEY_VACCINE_ID,vac_id);
                params.put(Constant.KEY_MODE,mode);
                params.put(Constant.KEY_BABY_ID,getBabyId);
                params.put(Constant.KEY_USER_CELL,getCell);
                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(VaccineSchedule.this);
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

                        Toast.makeText(VaccineSchedule.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(VaccineSchedule.this);
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
                Toast.makeText(VaccineSchedule.this, "No Vaccine Taken!", Toast.LENGTH_SHORT).show();
            }
            else {

                for (int i = 0; i < result.length(); i++) {

                    JSONObject jo = result.getJSONObject(i);
                    String vaccine_id = jo.getString(Constant.KEY_VACCINE_ID);
                    String mode = jo.getString(Constant.KEY_MODE);
                    String child_id = jo.getString(Constant.KEY_BABY_ID);

                    if (vaccine_id.equals("101") && mode.equals("1") && child_id.equals(getBabyId)) {

                        status1="Given";
                    } else {

                        status1="Not Given";
                    }


                    if (vaccine_id.equals("102") && mode.equals("1") && child_id.equals(getBabyId)) {

                        status2="Given";
                    } else {

                        status2="Not Given";
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}