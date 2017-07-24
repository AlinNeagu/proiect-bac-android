package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.R;

public class AddProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final Button addProfilBtn=(Button)findViewById(R.id.addProfil);
        addProfilBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                addProfil();
            }
        });
    }

    public void addProfil(){
        EditText numeProfil=(EditText)findViewById(R.id.numeProfil);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8080/ProiectBAC/profil?profil="+numeProfil.getText();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in met", Toast.LENGTH_LONG);
                            toast.show();

                        } catch (Exception e) {
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

    /*public void takeLicee(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8080/ProiectBAC/licee";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in met", Toast.LENGTH_LONG);
                            toast.show();
                            ObjectMapper objMapper=new ObjectMapper();
                            licee = objMapper.readValue(response,LiceePOJO.class);



                            List<String> listaLicee=new ArrayList<>();
                            for (LiceuPOJO l:licee.getLicee()) {
                                listaLicee.add(l.getNume());
                            }

                            adapter = new ArrayAdapter<String>(AddProfil.this,
                                    R.layout.simple_spinner_item,listaLicee);

                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                            Spinner spiner=(Spinner) findViewById(R.id.liceuSpiner);
                            spiner.setAdapter(adapter);


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
    }*/

}
