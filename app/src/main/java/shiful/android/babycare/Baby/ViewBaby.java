package shiful.android.babycare.Baby;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import shiful.android.babycare.Constant;
import shiful.android.babycare.Home.HomeActivity;
import shiful.android.babycare.R;

public class ViewBaby extends AppCompatActivity {
    ListView CustomList;
    String getCell;
    FloatingActionButton fab;
    private ProgressDialog loading;

    int MAX_SIZE=999;

    public String babyName[]=new String[MAX_SIZE];
    public String babyGender[]=new String[MAX_SIZE];
    public String babyBg[]=new String[MAX_SIZE];
    public String babyDob[]=new String[MAX_SIZE];
    public String babyBp[]=new String[MAX_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_baby);

        CustomList=(ListView)findViewById(R.id.baby_list);
        //imgNoData=(ImageView)findViewById(R.id.imgNoData);

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
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ViewBaby.this, AddBaby.class);
                startActivity(intent);
            }
        });

    }


    private void getData(String text) {

        //for showing progress dialog
        loading = new ProgressDialog(ViewBaby.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.VIEW_BABY_URL+getCell;

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
                        Toast.makeText(ViewBaby.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ViewBaby.this);
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
                Toast.makeText(ViewBaby.this, "No Data Available!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ViewBaby.this, HomeActivity.class);

                startActivity(intent);
                //imgNoData.setImageResource(R.drawable.nodata);
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String baby_name = jo.getString(Constant.KEY_BABY_NAME);
                    String baby_gender = jo.getString(Constant.KEY_BABY_GENDER);
                    String blood_group = jo.getString(Constant.KEY_BLOODGROUP);
                    String date_of_birth = jo.getString(Constant.KEY_DOB);
                    String birth_place = jo.getString(Constant.KEY_BP);

                    //insert data into array for put extra

                    babyName[i] = baby_name;
                    babyGender[i] = baby_gender;
                    babyBg[i] = blood_group;
                    babyDob[i] = date_of_birth;
                    babyBp[i] = birth_place;

                    //put value into Hashmap
                    HashMap<String, String> user_data = new HashMap<>();
                    user_data.put(Constant.KEY_BABY_NAME, baby_name);
                    user_data.put(Constant.KEY_BABY_GENDER, baby_gender);
                    user_data.put(Constant.KEY_BLOODGROUP, blood_group);
                    user_data.put(Constant.KEY_DOB, date_of_birth);
                    user_data.put(Constant.KEY_BP, birth_place);
                    list.add(user_data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewBaby.this, list, R.layout.baby_list_items,
                new String[]{Constant.KEY_BABY_NAME, Constant.KEY_BABY_GENDER},
                new int[]{R.id.txt_name, R.id.txt_gender});
        CustomList.setAdapter(adapter);
        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent=new Intent(ViewBaby.this,BabyDetailsActivity.class);
                intent.putExtra("name",babyName[position]);
                intent.putExtra("gender",babyGender[position]);
                intent.putExtra("bloodgroup",babyBg[position]);
                intent.putExtra("dateofbirth",babyDob[position]);
                intent.putExtra("birthplace",babyBp[position]);

                startActivity(intent);

            }
        });

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