package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.SpecializarePOJO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class AdaugaSpecializare extends AppCompatActivity {


    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();
    private String profilSelectat;
    Integer idProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_specializare);
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


        getProfiluri();
        final Button adaugaSpecializareBtn=(Button)findViewById(R.id.adaugaSpecializare);
        adaugaSpecializareBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                adaugaSpecializare(idProfil);
            }
        });
    }

    public void adaugaSpecializare(Integer idProfil){
        EditText numeSpecializare=(EditText)findViewById(R.id.numeSpecializare);
        String nume = numeSpecializare.getText().toString();

        JSONObject data = new JSONObject();
        SpecializarePOJO specializare = new SpecializarePOJO();
        specializare.setNume(nume);


        for (ProfilPOJO profilPOJO : profiluriPOJO) {
            if (profilSelectat.equals(profilPOJO.getDenumireProfil())){
                idProfil = profilPOJO.getIdProfil();
                specializare.setProfil(profilPOJO);
            }
        }


        try {
            data.put("idProfil", specializare.getProfil().getIdProfil());
            data.put("specializare", specializare.getNume());


        } catch (JSONException e) {
            e.printStackTrace();
        }



        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8080/ProiectBAC/specializare?idProfil="+specializare.getProfil().getIdProfil() + "&specializare="+ specializare.getNume();

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(GET, url,
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

    public void getProfiluri() {

        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluri";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {


                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {});
                            String[] listaProfiluri = new String[profiluriPOJO.size()];

                            int i = 0;
                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluri[i] = profilPOJO.getDenumireProfil();
                                i++;
                            }


                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugaSpecializare.this,
                                    R.layout.simple_spinner_item, listaProfiluri);



                            Spinner spinner = (Spinner) findViewById(R.id.spinner7);
                            spinner.setAdapter(adapter);


                            spinner.setOnItemSelectedListener(new OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {
                                    profilSelectat = parent.getItemAtPosition(position).toString();

                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, profilSelectat, Toast.LENGTH_LONG);
                                    toast.show();

                                }
                                public void onNothingSelected(AdapterView<?> parent)
                                {
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, "Nu ati selectat un profil!", Toast.LENGTH_LONG);
                                    toast.show();
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
                Toast toast = Toast.makeText(context, "error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
