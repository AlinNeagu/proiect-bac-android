package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceePOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceuPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

/**
 * Created by Iulia.Popa on 6/19/2017.
 */

/*@JsonIgnoreProperties(ignoreUnknown = true)*/
public class AdaugaLiceuActivity extends MainActivity{

    EditText numeLiceu, numeJudet;
    Button button;
    LiceePOJO liceePojo = new LiceePOJO();
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    String nume, judet;
    List<Integer> listaIdProfiluri = new ArrayList<>();
    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    List<ProfilPOJO> profiluri = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugaliceu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getProfiluri();
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


                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugaLiceuActivity.this,
                                    R.layout.simple_spinner_item, listaProfiluri);



                            Spinner spinner = (Spinner) findViewById(R.id.spinner3);
                            spinner.setAdapter(adapter);


                            spinner.setOnItemSelectedListener(new OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {
                                    String profilSelectat = parent.getItemAtPosition(position).toString();
                                    for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                        if (profilPOJO.getDenumireProfil()==profilSelectat) {
                                            listaIdProfiluri.add(profilPOJO.getIdProfil());
                                            profiluri.add(profilPOJO);
                                        }
                                    }
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, profilSelectat, Toast.LENGTH_LONG);
                                    toast.show();
                                    if(profilSelectat.equals(profilSelectat))
                                    {
                                        // do your stuff

                                    }
                                } // to close the onItemSelected
                                public void onNothingSelected(AdapterView<?> parent)
                                {
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, "Nu ati selectat un profil!", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });


                           Button buton = (Button)findViewById(R.id.button);
                            buton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    numeLiceu = (EditText)findViewById(R.id.numeLiceu);
                                    numeJudet = (EditText)findViewById(R.id.numeJudet);

                                   nume = numeLiceu.getText().toString();
                                    judet = numeJudet.getText().toString();
                                    // Code here executes on main thread after user presses button
                                    adaugaLiceu(profiluri,listaIdProfiluri,nume,judet);
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

    public void adaugaLiceu(List<ProfilPOJO>idProfil, List<Integer>listaId, String nume, String judet) {

        RequestQueue queue = Volley.newRequestQueue(this);
        LiceuPOJO liceu = new LiceuPOJO();
        liceu.setNume(nume);
        liceu.setJudet(judet);
        liceu.setIdProfilInt(listaId);

        JSONObject data = new JSONObject();
        JSONArray data1 = new JSONArray();
        for (int i = 0; i<listaId.size();i++){
                data1.put(listaId.get(i));
        }

        /*JSONArray data2 = new JSONArray();
        for (int i = 0; i< idProfil.size();i++){
            data2.put(idProfil.get(i));
        }*/
        try {
            data.put("nume", liceu.getNume());
            data.put("judet", liceu.getJudet());
           // data.put("idProfil",data2);
            data.put("idProfilInt",data1);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = "http://10.0.2.2:8080/ProiectBAC/liceu?liceu=" + data;

        //http://www.android-examples.com/create-listview-with-multiple-checkbox-in-android/
        // Request a string response from the provided URL.


        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

       Request stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "sunt in adauga liceu" , Toast.LENGTH_LONG);
                            toast.show();
                            pDialog.hide();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "error", Toast.LENGTH_LONG);
                toast.show();
                pDialog.hide();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
