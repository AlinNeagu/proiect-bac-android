package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ElevAndroidPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.EleviAndroidPOJO;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageButton addElev;
    private ImageButton search;
    private ListView mainListView;
    private ArrayAdapter adapter;
    private EditText searchText;
    private ArrayAdapter<String> listAdapter;
    EleviAndroidPOJO eleviPojo = new EleviAndroidPOJO();
    ObjectMapper objMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        addElev=(ImageButton)findViewById(R.id.addElev);

        addElev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,AdaugareElev.class);
                startActivity(i);
            }
        });

        searchText=(EditText) findViewById(R.id.search);
        search=(ImageButton) findViewById(R.id.searchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSearch();
            }
        });
//        Intent intent=new Intent(MainActivity.this,EditProfil.class);
//        intent.putExtra("id",2);
//        intent.putExtra("nume","Uman");
//        startActivity(intent);
        getElevi();
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

    public void onClickSearch(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8080/ProiectBAC/numeEleviBySearch?keyWord="+searchText.getText();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.


                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in met", Toast.LENGTH_LONG);
                            toast.show();
                            eleviPojo = objMapper.readValue(response, EleviAndroidPOJO.class);



                            String[] listaElevi = new String[eleviPojo.getElevi().size()];
                            int i = 0;
                            for (ElevAndroidPOJO  elevPojo : eleviPojo.getElevi()) {

                                listaElevi[i] = elevPojo.getNumeElev()+" "+  elevPojo.getPrenumeElev()+" "+ elevPojo.getInitialaTata();
                                i++;
                            }
                            //
                            adapter = new ArrayAdapter<String>(MainActivity.this,
                                    R.layout.simplerow, listaElevi);

                            ListView listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this,Date_Elev.class);
                                    intent.putExtra("id",eleviPojo.getElevi().get(position).getId());
                                    //based on item add info to intent
                                    startActivity(intent);
                                }
                            });


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`t work", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        queue.add(stringRequest);
    }
    public void getElevi() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/numeElevi";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in met numeElevi", Toast.LENGTH_LONG);
                            toast.show();
                            eleviPojo = objMapper.readValue(response, EleviAndroidPOJO.class);


                            String[] listaElevi = new String[eleviPojo.getElevi().size()];
                            int i = 0;
                            for (ElevAndroidPOJO elevPojo : eleviPojo.getElevi()) {

                                listaElevi[i] = elevPojo.getNumeElev()+" "+  elevPojo.getPrenumeElev()+" "+ elevPojo.getInitialaTata();
                                i++;

                            }
                            ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this,
                                    R.layout.simplerow, listaElevi);

                            ListView listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(MainActivity.this,Date_Elev.class);
                                    intent.putExtra("id",eleviPojo.getElevi().get(position).getId());
                                    //based on item add info to intent
                                    startActivity(intent);
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`t work nume elevi", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}
