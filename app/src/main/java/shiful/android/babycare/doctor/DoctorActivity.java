package shiful.android.babycare.doctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/shiful/android/babycare/doctor/DoctorActivity.java
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
>>>>>>> origin/master:app/src/main/java/shiful/android/babycare/Doctor/DoctorActivity.java
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
import shiful.android.babycare.home.HomeActivity;
import shiful.android.babycare.R;

public class DoctorActivity extends AppCompatActivity {
    ListView CustomList;
    private ProgressDialog loading;
    Button btnSearch;
    EditText txtSearch;
    int MAX_SIZE=999;

    public String docName[]=new String[MAX_SIZE];
    public String docPhone[]=new String[MAX_SIZE];
    public String docAddress[]=new String[MAX_SIZE];
    public String docDescription[]=new String[MAX_SIZE];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("CHILD SPECIALIST");
        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        btnSearch=findViewById(R.id.btn_search);
        txtSearch=findViewById(R.id.txt_search);
        CustomList=(ListView)findViewById(R.id.doctor_list);

        //call function to get data
        getData("");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String search=txtSearch.getText().toString();

                if (search.isEmpty())
                {
                    Toasty.info(DoctorActivity.this, "Please enter doctor name or location!", Toast.LENGTH_LONG).show();
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
        loading = new ProgressDialog(DoctorActivity.this);
        loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Loading");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.VIEW_DOC_URL+"&text="+text;
        Log.d("url",URL);
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
                        Toasty.error(DoctorActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DoctorActivity.this);
        requestQueue.add(stringRequest);

    }



    private void showJSON(String response) {

        //Create json object for receiving json data
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


            if (result.length()==0)
            {
                Toasty.info(DoctorActivity.this, "No Data Available!", Toast.LENGTH_LONG).show();
            }

            else {

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String doctor_name = jo.getString(Constant.KEY_DOCTOR_NAME);
                    String doctor_cell = jo.getString(Constant.KEY_DOCTOR_CELL);
                    String doctor_address = jo.getString(Constant.KEY_DOCTOR_ADDRESS);
                    String doctor_description = jo.getString(Constant.KEY_DOCTOR_DESCRIPTION);

                    //insert data into array for put extra

                    docName[i] = doctor_name;
                    docPhone[i] = doctor_cell;
                    docAddress[i] = doctor_address;
                    docDescription[i] = doctor_description;

                    //put value into Hashmap
                    HashMap<String, String> doc_data = new HashMap<>();
                    doc_data.put(Constant.KEY_DOCTOR_NAME, doctor_name);
                    doc_data.put(Constant.KEY_DOCTOR_CELL, doctor_cell);
                    doc_data.put(Constant.KEY_DOCTOR_ADDRESS, doctor_address);
                    doc_data.put(Constant.KEY_DOCTOR_DESCRIPTION, doctor_description);

                    list.add(doc_data);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                DoctorActivity.this, list, R.layout.doctor_list_items,
                new String[]{Constant.KEY_DOCTOR_NAME, Constant.KEY_DOCTOR_CELL},
                new int[]{R.id.txt_doc_name, R.id.txt_doc_cell});
        CustomList.setAdapter(adapter);

        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


                Intent intent=new Intent(DoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("name",docName[position]);
                intent.putExtra("cell",docPhone[position]);
                intent.putExtra("address",docAddress[position]);
                intent.putExtra("description",docDescription[position]);

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