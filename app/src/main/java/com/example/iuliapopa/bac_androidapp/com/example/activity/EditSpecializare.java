package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.os.Bundle;
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

import com.android.volley.Request;
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

public class EditSpecializare extends AppCompatActivity {


    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();
    ArrayAdapter adapter;

    private EditText numeSpecializare;
    private EditText numeProfil;
    private String id;
    String[] listaProfiluriSpinner = new String[profiluriPOJO.size()];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_specializare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getBundleExtra("date");
        String nume = b.getString("nume");
        String profil = b.getString("profil");
        numeSpecializare = (EditText) findViewById(R.id.numeSpecializare);
        numeSpecializare.setText(nume);
        numeProfil = (EditText) findViewById(R.id.numeProfil);
        numeProfil.setText(profil);
        id = b.getString("id");
        afiseazaProfil(profil);

        Button buton = (Button)findViewById(R.id.editSpecializare);
        buton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String numeSpec = numeSpecializare.getText().toString();
                String numeP = numeProfil.getText().toString();
                try {
                    editeazaSpecializare(numeSpec,numeP);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void afiseazaProfil(String profil){

        final String profilSelectat = profil;
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
                            Toast toast = Toast.makeText(context, "getProfiluri", Toast.LENGTH_LONG);
                            toast.show();

                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {});
                            listaProfiluriSpinner = new String[profiluriPOJO.size()];

                            int i = 0;
                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluriSpinner[i] = profilPOJO.getDenumireProfil();
                                i++;
                            }

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditSpecializare.this,
                                    R.layout.simple_spinner_item, listaProfiluriSpinner);


                            Spinner spinner = (Spinner) findViewById(R.id.spinner8);
                            spinner.setAdapter(adapter);
                            spinner.clearFocus();
                            int position = adapter.getPosition(profilSelectat);
                            spinner.setSelection(position);

                            spinner.setOnItemSelectedListener(new OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {
                                    numeProfil.setText(parent.getItemAtPosition(position).toString());
                                }

                                public void onNothingSelected(AdapterView<?> parent)
                                {
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, "aa", Toast.LENGTH_LONG);
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
                Toast toast = Toast.makeText(context, "doesn`tttt work", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);;
    }


    public void editeazaSpecializare(String nume, String profil){

        RequestQueue queue = Volley.newRequestQueue(this);

        ProfilPOJO profilSpecializare = new ProfilPOJO();
        JSONObject dataProfil = new JSONObject();
        try {
            for (ProfilPOJO p : profiluriPOJO) {
                if (p.getDenumireProfil().equals(profil)) {
                    dataProfil.put("denumireProfil", p.getDenumireProfil());
                    dataProfil.put("idProfil",p.getIdProfil());
                    dataProfil.put("currentId",dataProfil.NULL);
                    dataProfil.put("specializari",dataProfil.NULL);
                    profilSpecializare = p;
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        SpecializarePOJO spec = new SpecializarePOJO();
        Integer idSpecializare = Integer.parseInt(id);
        spec.setId(idSpecializare);
        spec.setNume(nume);
        spec.setProfil(profilSpecializare);

        JSONObject data = new JSONObject();

        try {
            data.put("id",spec.getId());
            data.put("nume", spec.getNume());
            data.put("profil",dataProfil);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = "http://10.0.2.2:8080/ProiectBAC/editSpecializare?specializare=" + data;

        Request stringRequest = new StringRequest(GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "sunt in edit specializare" , Toast.LENGTH_LONG);
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


            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
