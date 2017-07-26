package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iulia.popa on 6/6/2017.
 */

public class ProfilActivity extends MainActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiluri);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getDiscipline();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent myIntent;
        switch (item.getItemId()) {
            case R.id.action_getElevi:
                myIntent = new Intent(this,MainActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.action_getLicee:
                myIntent = new Intent(this,LiceuActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.action_getDiscipline:
                myIntent = new Intent(this,DisciplinaActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.action_getProfiluri:
                myIntent = new Intent(this,ProfilActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.action_getSpecializari:
                myIntent = new Intent(this,SpecializareActivity.class);
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getDiscipline() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://169.254.154.210:8080/ProiectBAC/getProfiluri";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intraaaaat in met", Toast.LENGTH_LONG);
                            toast.show();

                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {});
                            String[] listaProfiluri = new String[profiluriPOJO.size()];

                            int i = 0;
                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluri[i] = profilPOJO.getDenumireProfil();
                                i++;
                            }


                            ArrayAdapter adapter = new ArrayAdapter<String>(ProfilActivity.this,
                                    R.layout.simplerow, listaProfiluri);

                            ListView listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(ProfilActivity.this,EditProfil.class);
                                    intent.putExtra("id",eleviPojo.getElevi().get(position).getId());
                                    //based on item add info to intent
                                    startActivity(intent);
                                }
                            });
                        } catch (IOException e) {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, e.toString(), Toast.LENGTH_LONG);
                            toast.show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`tttt work", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}

