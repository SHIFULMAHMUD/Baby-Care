package shiful.android.babycare.healthcenter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

import es.dmoral.toasty.Toasty;
import shiful.android.babycare.Constant;
import shiful.android.babycare.doctor.DoctorActivity;
import shiful.android.babycare.home.HomeActivity;
import shiful.android.babycare.R;


public class HealthCenterActivity extends AppCompatActivity {
    ListView CustomList;
    private ProgressDialog loading;
    Button btnSearch;
    EditText txtSearch;
    int MAX_SIZE=999;

    public String hcName[]=new String[MAX_SIZE];
    public String hcPhone[]=new String[MAX_SIZE];
    public String hcLocation[]=new String[MAX_SIZE];
    public String hcWebsite[]=new String[MAX_SIZE];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_center);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("HEALTH CENTER");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        btnSearch=findViewById(R.id.button_search);
        txtSearch=findViewById(R.id.text_search);
        CustomList=(ListView)findViewById(R.id.hc_list);
        //call function to get data
        getData("");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search=txtSearch.getText().toString();

                if (search.isEmpty())
                {
                    Toasty.info(HealthCenterActivity.this, "Please enter hospital name or location!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    getData(search);
                }

            }
        });

    }


    private void getData(String text) {

        //for showing progress dialog
        loading = new ProgressDialog(shiful.android.babycare.healthcenter.HealthCenterActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.VIEW_HEALTH_CENTER_URL+"&text="+text;

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
                        Toasty.error(shiful.android.babycare.healthcenter.HealthCenterActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(shiful.android.babycare.healthcenter.HealthCenterActivity.this);
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
                Toasty.info(shiful.android.babycare.healthcenter.HealthCenterActivity.this, "No Data Available!", Toast.LENGTH_LONG).show();
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String hc_name = jo.getString(Constant.KEY_HC_NAME);
                    String hc_phone = jo.getString(Constant.KEY_HC_PHONE);
                    String hc_location = jo.getString(Constant.KEY_HC_LOCATION);
                    String hc_website = jo.getString(Constant.KEY_HC_WEBSITE);

                    //insert data into array for put extra

                    hcName[i] = hc_name;
                    hcPhone[i] = hc_phone;
                    hcLocation[i] = hc_location;
                    hcWebsite[i] = hc_website;

                    //put value into Hashmap
                    HashMap<String, String> hc_data = new HashMap<>();
                    hc_data.put(Constant.KEY_HC_NAME, hc_name);
                    hc_data.put(Constant.KEY_HC_PHONE, hc_phone);
                    hc_data.put(Constant.KEY_HC_LOCATION, hc_location);
                    hc_data.put(Constant.KEY_HC_WEBSITE, hc_website);

                    list.add(hc_data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                shiful.android.babycare.healthcenter.HealthCenterActivity.this, list, R.layout.healthcare_list_items,
                new String[]{Constant.KEY_HC_NAME, Constant.KEY_HC_PHONE},
                new int[]{R.id.txt_hc_name, R.id.txt_hc_cell});
        CustomList.setAdapter(adapter);

        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent=new Intent(shiful.android.babycare.healthcenter.HealthCenterActivity.this, HealthCareDetails.class);
                intent.putExtra("name",hcName[position]);
                intent.putExtra("phone",hcPhone[position]);
                intent.putExtra("location",hcLocation[position]);
                intent.putExtra("website",hcWebsite[position]);

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
