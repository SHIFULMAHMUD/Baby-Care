package shiful.android.babycare.vaccine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

import es.dmoral.toasty.Toasty;
import shiful.android.babycare.Constant;
import shiful.android.babycare.home.HomeActivity;
import shiful.android.babycare.R;


public class VaccineActivity extends AppCompatActivity {
    ListView CustomList;
    private ProgressDialog loading;

    int MAX_SIZE=999;

    public String vacName[]=new String[MAX_SIZE];
    public String vacDisease[]=new String[MAX_SIZE];
    public String vacDoseno[]=new String[MAX_SIZE];
    public String vacDoseinterval[]=new String[MAX_SIZE];
    public String vacStarttime[]=new String[MAX_SIZE];
    public String vacRoute[]=new String[MAX_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("VACCINES");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button

        CustomList=(ListView)findViewById(R.id.vaccine_list);
        //imgNoData=(ImageView)findViewById(R.id.imgNoData);


/*getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Contacts");*/



        //call function to get data
        getData("");

    }


    private void getData(String text) {

        //for showing progress dialog
        loading = new ProgressDialog(VaccineActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.VIEW_VACCINE_URL;

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
                        Toasty.error(VaccineActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(VaccineActivity.this);
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
                Toasty.info(VaccineActivity.this, "No Data Available!", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(VaccineActivity.this, HomeActivity.class);

                startActivity(intent);
                //imgNoData.setImageResource(R.drawable.nodata);
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String vaccine_name = jo.getString(Constant.KEY_VACCINE_NAME);
                    String disease = jo.getString(Constant.KEY_DISEASE_NAME);
                    String dose_no = jo.getString(Constant.KEY_DOSE_NO);
                    String dose_interval = jo.getString(Constant.KEY_DOSE_INTERVAL);
                    String start_time = jo.getString(Constant.KEY_START_TIME);
                    String route = jo.getString(Constant.KEY_INJECT_ROUTE);

                    //insert data into array for put extra

                    vacName[i] = vaccine_name;
                    vacDisease[i] = disease;
                    vacDoseno[i] = dose_no;
                    vacDoseinterval[i] = dose_interval;
                    vacStarttime[i] = start_time;
                    vacRoute[i] = route;

                    //put value into Hashmap
                    HashMap<String, String> vac_data = new HashMap<>();
                    vac_data.put(Constant.KEY_VACCINE_NAME, vaccine_name);
                    vac_data.put(Constant.KEY_DISEASE_NAME, disease);
                    vac_data.put(Constant.KEY_DOSE_NO, dose_no);
                    vac_data.put(Constant.KEY_DOSE_INTERVAL, dose_interval);
                    vac_data.put(Constant.KEY_START_TIME, start_time);
                    vac_data.put(Constant.KEY_INJECT_ROUTE, route);

                    list.add(vac_data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                VaccineActivity.this, list, R.layout.vaccine_list_items,
                new String[]{Constant.KEY_VACCINE_NAME},
                new int[]{R.id.txt_vac_name});
        CustomList.setAdapter(adapter);

        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent=new Intent(VaccineActivity.this, VaccineDetailsActivity.class);
                intent.putExtra("name",vacName[position]);
                intent.putExtra("disease",vacDisease[position]);
                intent.putExtra("doseno",vacDoseno[position]);
                intent.putExtra("doseinterval",vacDoseinterval[position]);
                intent.putExtra("starttime",vacStarttime[position]);
                intent.putExtra("route",vacRoute[position]);

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
