package shiful.android.babycare.vaccine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
<<<<<<< HEAD:app/src/main/java/shiful/android/babycare/vaccine/ViewRequest.java
import android.support.v7.app.AppCompatActivity;
=======

import androidx.appcompat.app.AppCompatActivity;
>>>>>>> origin/master:app/src/main/java/shiful/android/babycare/Vaccine/ViewRequest.java
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

<<<<<<< HEAD:app/src/main/java/shiful/android/babycare/vaccine/ViewRequest.java
import es.dmoral.toasty.Toasty;
=======
>>>>>>> origin/master:app/src/main/java/shiful/android/babycare/Vaccine/ViewRequest.java
import shiful.android.babycare.Constant;
import shiful.android.babycare.R;

public class ViewRequest extends AppCompatActivity {
    ListView CustomList;
    String getCell;
    private ProgressDialog loading;

    int MAX_SIZE=999;

    public String vacName[]=new String[MAX_SIZE];
    public String babyName[]=new String[MAX_SIZE];
    public String parentName[]=new String[MAX_SIZE];
    public String parentCell[]=new String[MAX_SIZE];
    public String parentAddress[]=new String[MAX_SIZE];
    public String vacStatus[]=new String[MAX_SIZE];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("REQUESTED VACCINES");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        CustomList=(ListView)findViewById(R.id.vac_req_list);

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences;
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        getCell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");

        //Log
        Log.d("SP_CELL",getCell);


        //call function to get data
        getData("");

    }


    private void getData(String text) {

        //for showing progress dialog
        loading = new ProgressDialog(shiful.android.babycare.vaccine.ViewRequest.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.VIEW_VACCINE_REQ_URL+getCell;

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
                        Toasty.error(shiful.android.babycare.vaccine.ViewRequest.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(shiful.android.babycare.vaccine.ViewRequest.this);
        requestQueue.add(stringRequest);

    }



    private void showJSON(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


            if (result.length()==0)
            {
                Toasty.info(shiful.android.babycare.vaccine.ViewRequest.this, "You have no ordered vaccine!", Toast.LENGTH_LONG).show();

            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String vac_name = jo.getString(Constant.KEY_VACCIN_NAME);
                    String child_name = jo.getString(Constant.KEY_CHILD_NAME);
                    String parent_name = jo.getString(Constant.KEY_GUARDIAN_NAME);
                    String parent_cell = jo.getString(Constant.KEY_GUARDIAN_CELL);
                    String parent_address = jo.getString(Constant.KEY_GUARDIAN_ADDRESS);
                    String status = jo.getString(Constant.KEY_STATUS);

                    //insert data into array for put extra
                    vacName[i]=vac_name;
                    babyName[i] = child_name;
                    parentName[i] = parent_name;
                    parentCell[i] = parent_cell;
                    parentAddress[i] = parent_address;
                    vacStatus[i] = status;

                    if (status.equals("0")){
                        status="Pending";
                    }else if (status.equals("1")){
                        status="Taken";
                    }else if (status.equals("2")){
                        status="Canceled";
                    }

                    //put value into Hashmap
                    HashMap<String, String> user_data = new HashMap<>();
                    user_data.put(Constant.KEY_VACCIN_NAME, vac_name);
                    user_data.put(Constant.KEY_CHILD_NAME, child_name);
                    user_data.put(Constant.KEY_GUARDIAN_NAME, parent_name);
                    user_data.put(Constant.KEY_GUARDIAN_CELL, parent_cell);
                    user_data.put(Constant.KEY_GUARDIAN_ADDRESS, parent_address);
                    user_data.put(Constant.KEY_STATUS, status);
                    list.add(user_data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                shiful.android.babycare.vaccine.ViewRequest.this, list, R.layout.vac_req_list,
                new String[]{Constant.KEY_VACCIN_NAME, Constant.KEY_CHILD_NAME,Constant.KEY_STATUS},
                new int[]{R.id.txt_vacname, R.id.txt_babyname,R.id.txt_vacst});
        CustomList.setAdapter(adapter);

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