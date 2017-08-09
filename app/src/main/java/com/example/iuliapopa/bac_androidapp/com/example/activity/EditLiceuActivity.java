package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceuPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.SpecializarePOJO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class EditLiceuActivity extends AppCompatActivity {


    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();
    List<String> listaProfiluri = new ArrayList<>();
    List<SpecializarePOJO> specializariPOJO = new ArrayList<>();
    List<String> listaSpecializari = new ArrayList<>();

    ArrayAdapter adapter;
    EditText numeLiceu;
    EditText numeJudet;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_liceu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.second_toolbar);
        setSupportActionBar(toolbar);

        Intent i = new Intent();
        //Bundle b = i.getBundleExtra("date");
        Bundle b = getIntent().getBundleExtra("date");
        String nume = b.getString("nume");
        getProfilForLiceu(nume);
        String judet = b.getString("judet");
        numeLiceu = (EditText) findViewById(R.id.numeLiceu);
        numeLiceu.setText(nume);
        numeJudet = (EditText) findViewById(R.id.numeJudet);
        numeJudet.setText(judet);
        id = b.getString("id");
        getProfiluri();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }
    public void getProfilForLiceu(String nume) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriForUnitate?unitate=" + nume;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in getProfilforUnitate", Toast.LENGTH_LONG);
                            toast.show();

                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {
                            });

                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluri.add (profilPOJO.getDenumireProfil());
                            }


                            ArrayAdapter adapter = new ArrayAdapter<String>(EditLiceuActivity.this,
                                    R.layout.simplerow, listaProfiluri);

                            ListView listView = (ListView) findViewById(R.id.listaProfiluri);
                            listView.setAdapter(adapter);
                        } catch (Exception e) {

                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "exceptieee", Toast.LENGTH_LONG);
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
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intraaaaat in met", Toast.LENGTH_LONG);
                            toast.show();

                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {});
                            final String[] listaProfiluriSpinner = new String[profiluriPOJO.size()];

                            int i = 0;
                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluriSpinner[i] = profilPOJO.getDenumireProfil();
                                i++;
                            }


                            ArrayAdapter adapter = new ArrayAdapter<String>(EditLiceuActivity.this,
                                    R.layout.simple_spinner_item, listaProfiluriSpinner);


                            Spinner spinner = (Spinner) findViewById(R.id.spinner4);
                            spinner.setAdapter(adapter);
                            spinner.clearFocus();
                            spinner.setOnItemSelectedListener(new OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {
                                    String profilSelectat;
                                    if (position == 0){
                                        profilSelectat = "";
                                    }else {
                                        profilSelectat = parent.getItemAtPosition(position).toString();
                                    }
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, profilSelectat, Toast.LENGTH_LONG);
                                    toast.show();

                                    if(!(profilSelectat.isEmpty()))
                                    {
                                        listaProfiluri.add(profilSelectat);
                                        // do your stuff
                                        ArrayAdapter adapter = new ArrayAdapter<String>(EditLiceuActivity.this,
                                                R.layout.simplerow, listaProfiluri);

                                        ListView listView = (ListView) findViewById(R.id.listaProfiluri);
                                        listView.setAdapter(adapter);

                                    }
                                } // to close the onItemSelected
                                public void onNothingSelected(AdapterView<?> parent)
                                {
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, "Nu ati selectat un profil!", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });


                           // http://localhost:8080/ProiectBAC/editLiceu?liceu={"id":7,"nume":"Colegiul+National+\"Nicolae+Grigorescu\"","judet":"prahova","idProfil":[{"denumireProfil":"Real","idProfil":1,"currentId":1,"specializari":null},{"denumireProfil":"Uman","idProfil":2,"currentId":3,"specializari":null}],"idProfilInt":null}
                            Button buton = (Button)findViewById(R.id.editLiceu);
                            buton.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    List<ProfilPOJO> profiluri = new ArrayList<>();
                                     List<Integer> listaIdProfiluri = new ArrayList<>();
                                    for (int j = 0; j < listaProfiluri.size();j++){
                                        for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                            if (listaProfiluri.get(j).equals(profilPOJO.getDenumireProfil())){
                                                profiluri.add(profilPOJO);
                                                listaIdProfiluri.add(profilPOJO.getIdProfil());
                                            }
                                        }
                                    }
                                    numeLiceu = (EditText)findViewById(R.id.numeLiceu);
                                    numeJudet = (EditText)findViewById(R.id.numeJudet);

                                    String nume = numeLiceu.getText().toString();
                                    String judet = numeJudet.getText().toString();
                                    // Code here executes on main thread after user presses button
                                    try {
                                        editeazaLiceu(profiluri,listaIdProfiluri,nume,judet);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

    public void getSpecializari(String profil){
        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" + profil;

        // Request a string response from the provided URL.
        StringRequest stringRequest =new StringRequest(GET, url, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                try {
                    specializariPOJO = objMapper.readValue(response, new TypeReference<List<SpecializarePOJO>>() {});

                    Context context = getApplicationContext();
                    Toast toast = Toast.makeText(context, "am intrat in getSpecializare", Toast.LENGTH_LONG);
                    toast.show();
                    listaSpecializari.clear();

                    for (SpecializarePOJO specializarePOJO : specializariPOJO) {
                        listaSpecializari.add(specializarePOJO.getNume());
                    }


                    ArrayAdapter adapter = new ArrayAdapter<String>(EditLiceuActivity.this,
                            R.layout.simplerow, listaSpecializari);



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
                Toast toast = Toast.makeText(context, error.toString(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public void editeazaLiceu(List<ProfilPOJO> idProfil, List<Integer> listaId, String nume, String judet) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(this);
        LiceuPOJO liceu = new LiceuPOJO();

        Integer idLiceu = Integer.parseInt(id);
        liceu.setId(idLiceu);
        liceu.setNume(nume);
        liceu.setJudet(judet);
        liceu.setIdProfil(idProfil);
        liceu.setIdProfilInt(listaId);


        JSONObject data = new JSONObject();
        JSONArray data1 = new JSONArray();
        for (int i = 0; i<listaId.size();i++){
            data1.put(listaId.get(i));
        }


        JSONArray data3 = new JSONArray();
        /*for (int i = 0; i< idProfil.size();i++){
            data2.put(idProfil.get(i));
        }*/
       /* int k = 0;
        for (ProfilPOJO p : idProfil){
            data2.put("denumireProfil", p.getDenumireProfil());
            data2.put("idProfil",p.getIdProfil());
            data2.put("currentId", p.getIdProfil());
            data2.put("specializari", p.getSpecializari());
            data3.put(k,data2);
            k++;
        }*/

        for (int i = 0; i<idProfil.size();i++){
            JSONObject data2 = new JSONObject();
            data2.put("denumireProfil", idProfil.get(i).getDenumireProfil());
            //getSpecializari(idProfil.get(i).getDenumireProfil());
            data2.put("idProfil",idProfil.get(i).getIdProfil());
            data2.put("currentId", idProfil.get(i).getIdProfil());
            //idProfil.get(i).setSpecializari(null);
            data2.put("specializari", data2.NULL);
            data3.put(i,data2);
        }

        try {
            data.put("id",liceu.getId());
            data.put("nume", liceu.getNume());
            data.put("judet", liceu.getJudet());
            data.put("idProfil",data3);
            data.put("idProfilInt",data.NULL);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = "http://10.0.2.2:8080/ProiectBAC/editLiceu?liceu=" + data;

        //http://www.android-examples.com/create-listview-with-multiple-checkbox-in-android/
        // Request a string response from the provided URL.


        Request stringRequest = new StringRequest(GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "sunt in edit liceu" , Toast.LENGTH_LONG);
                            toast.show();

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

                //http://localhost:8080/ProiectBAC/editLiceu?liceu={"id":3,"nume":"Colegiul+National+
                // \"Dr.+Ioan+Mesota\"","judet":"Brasovvv","idProfil":
                // [{"denumireProfil":"Real","idProfil":1,"currentId":3,"specializari":null},
                // {"denumireProfil":"Uman","idProfil":2,"currentId":2,"specializari":null}],
                // "idProfilInt":null}
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



}
