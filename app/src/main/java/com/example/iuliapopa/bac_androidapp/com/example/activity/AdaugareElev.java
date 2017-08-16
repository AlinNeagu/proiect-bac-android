package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.iuliapopa.bac_androidapp.com.example.pojo.*;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProbaDTO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.SpecializarePOJO;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.iuliapopa.bac_androidapp.R.id.textAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textInitialaIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textLiceu;
import static com.example.iuliapopa.bac_androidapp.R.id.textMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textModerna;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaModerna;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textNumeIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textPrenumeIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textProfil;
import static com.example.iuliapopa.bac_androidapp.R.id.textSpecializare;

public class AdaugareElev extends AppCompatActivity {

    LiceePOJO liceePojo= new LiceePOJO();
    ProfilPOJO[] profilPojo;
    SpecializarePOJO[] specializarePojo;
    ProbePOJO probe;




    private String textLiceuAles;
    private String textProfilAles;
    private String textSpecializareAleasa;
    private String textMaternaAleasa;
    private String textObligatoriuAleasa;
    private String textAlegereAleasa;
    private String textModernaAleasa;
    private String textNumeElev;
    private String textPrenumeElev;
    private String textInitialaTata;
    private Double textNotaMaternaa;
    private Double textNotaContestatieMaternaa;
    private Double textNotaObligatoriuu;
    private Double textNotaContestatieObligatoriuu;
    private Double textNotaAlegeree;
    private Double textNotaContestatieAlegeree;
    private Double textNotaModernaa;

    ObjectMapper objMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_elev);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        getNumeLicee();

        Button clickButton = (Button) findViewById(R.id.adaugareElev);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("message","Ai apasat pe buton");
                insertElev();

                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Elevul a fost adaugat!", Toast.LENGTH_LONG);
                toast.show();

                goToMain();

            }


        });
    }

    void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
    public void insertElev(){

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        AddElevDTO elev= null;
        String elevSerialized="";
        try {

            ProbaDTO probaMaterna = new ProbaDTO();
            ProbaDTO probaObligatorie = new ProbaDTO();
            ProbaDTO probaAlegere =  new ProbaDTO();
            ProbaDTO probaModerna =new ProbaDTO();
            EditText numeElev = (EditText) findViewById(textNumeIn);
            textNumeElev = numeElev.getText().toString();
            Log.e("message", "numele este: " + textNumeElev);
            EditText prenumeElev = (EditText) findViewById(textPrenumeIn);
            textPrenumeElev = prenumeElev.getText().toString();
            Log.e("message", "prenume este: " + textPrenumeElev);
            EditText initialaTata = (EditText) findViewById(textInitialaIn);
            textInitialaTata = initialaTata.getText().toString();
            Log.e("message", "initiala este: " + textInitialaTata);
            Log.e("message", "liceu este: " + textLiceuAles);
            Log.e("message", "profil este: " + textProfilAles);
            Log.e("message", "spec este: " + textSpecializareAleasa);
            Log.e("message", "materna este: " + textMaternaAleasa);
            EditText textMaternaNota = (EditText) findViewById(textNotaMaterna);
            Log.e("message", "hello, ");
            textNotaMaternaa = Double.parseDouble(textMaternaNota.getText().toString());
            Log.e("'message", "notamaterna"+textNotaMaternaa);
            EditText textMaternaContestatie = (EditText) findViewById(textContestatieMaterna);
            textNotaContestatieMaternaa = Double.parseDouble(textMaternaContestatie.getText().toString());
            Log.e("message","obligatoriu este: " +textObligatoriuAleasa);
            EditText textObligatoriuNota = (EditText) findViewById(textNotaObligatorie);
            textNotaObligatoriuu = Double.parseDouble(textObligatoriuNota.getText().toString());
            EditText textObligatoriuContestatie = (EditText) findViewById(textContestatieObligatorie);
            textNotaContestatieObligatoriuu = Double.parseDouble(textObligatoriuContestatie.getText().toString());
            Log.e("message","alegere este: " +textAlegereAleasa);
            EditText alegereNota = (EditText) findViewById(textNotaAlegere);
            textNotaAlegeree = Double.parseDouble(alegereNota.getText().toString());
            EditText alegereContestatie = (EditText) findViewById(textContestatieAlegere);
            textNotaContestatieAlegeree = Double.parseDouble(alegereContestatie.getText().toString());
            Log.e("message", "moderna este" +textModernaAleasa);
            EditText modernaNota = (EditText) findViewById(textNotaModerna);
            textNotaModernaa = Double.parseDouble(modernaNota.getText().toString());
            // elev.setNumeElev(numeElev.getText().toString());
            Log.e("message","numele NENUL este!!! "+textNumeElev);
            elev= new AddElevDTO();
            elev.setNumeElev(textNumeElev);
            elev.setPrenumeElev(textPrenumeElev);
            elev.setInitialaTata(textInitialaTata);
            elev.setDenumireUnitate(textLiceuAles);
            elev.setDenumireSpecializare(textSpecializareAleasa);

            probaMaterna.setNume(textMaternaAleasa);
            probaMaterna.setNota(textNotaMaternaa);
            probaMaterna.setNotaContestatie(textNotaContestatieMaternaa);

            probaObligatorie.setNume(textObligatoriuAleasa);
            probaObligatorie.setNota(textNotaObligatoriuu);
            probaObligatorie.setNotaContestatie(textNotaContestatieObligatoriuu);

            probaAlegere.setNume(textAlegereAleasa);
            probaAlegere.setNota(textNotaAlegeree);
            probaAlegere.setNotaContestatie(textNotaContestatieAlegeree);

            probaModerna.setNume(textModernaAleasa);
            probaModerna.setNota(textNotaModernaa);

            List<ProbaDTO> probe = new ArrayList<ProbaDTO>();
            probe.add(probaMaterna);
            probe.add(probaObligatorie);
            probe.add(probaAlegere);
            probe.add(probaModerna);

            elev.setProbe(probe);
            Log.e("message", "Elevul este: " +elev);


            ObjectMapper objMapper = new ObjectMapper();
            elevSerialized = objMapper.writeValueAsString(elev);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("message", "Eroaree", e);

        }


        String url = "http://10.0.2.2:8080/ProiectBAC/insertElev?elev=" +elevSerialized  ;

        Request stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Log.e("message", "sunt in adaugare Elev");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override


            public void onErrorResponse(VolleyError error) {
                Log.e("message", "Eroare de adaugare in request", error);

            }
        });


        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("message", "nu merge ot add elev");
            }
        };

        queue.add(stringRequest);
    }




    public void getNumeLicee(){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/numeLicee";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la licee: " +response);
                            liceePojo = objMapper.readValue(response, LiceePOJO.class);


                            List<String> listaLicee = new ArrayList<String>();
                            listaLicee.add("");

                            for (LiceuPOJO liceuPojo : liceePojo.getLicee()) {
                                // UnitateDeInvatamant unitate = new UnitateDeInvatamant();
                                // unitate.setIdUnitate(liceuPojo.getId());
                                // unitate.setDenumireUnitate(liceuPojo.getNume());
                                listaLicee.add(liceuPojo.getNume());

                            }


                            final Spinner spinnerLicee = (Spinner)findViewById(textLiceu);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1, listaLicee);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            Log.e("mesage", "inainte de set liceu!!!!!" );

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/numeLicee";
                                    textLiceuAles = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "liceul va fi!!!!!" +textLiceuAles);
                                    getProfiluriForUnitate();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getProfiluriForUnitate(){
        Log.e("message", "############Liceul ales "+textLiceuAles);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriForUnitate?unitate=" +textLiceuAles  ;




        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseTrial) {
                        // Display the response string.
                        try {

                            Log.e("message","profil trial: " +responseTrial);
                            profilPojo = objMapper.readValue(responseTrial, ProfilPOJO[].class);
                            Log.e("message", "dupa mapper: ");

                            List<String> listaProfiluri = new ArrayList<String>();
                            listaProfiluri.add("");
                            for (ProfilPOJO profilLiceu : profilPojo) {
                                listaProfiluri.add(profilLiceu.getDenumireProfil());

                            }

                            final Spinner spinnerProfiluri = (Spinner)findViewById(textProfil);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1, listaProfiluri);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerProfiluri.setAdapter(adapter);

                            Log.e("mesage", "inainte de set!!!!!" );

                            spinnerProfiluri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriForUnitate?unitate=" +textLiceuAles;
                                    textProfilAles = spinnerProfiluri.getSelectedItem().toString();
                                    Log.e("mesage", "profilul va fi!!!!!" +textProfilAles);
                                    getSpecializariForProfil();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });
                            Log.e("mesage", "dupa set!!!!!" );

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("message", "Eroaree", e);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getSpecializariForProfil(){
        Log.e("message", "############Profilul ales "+textProfilAles);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" +textProfilAles  ;




        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseTrial) {
                        // Display the response string.
                        try {

                            Log.e("message","Specializare: " +responseTrial);
                            specializarePojo = objMapper.readValue(responseTrial, SpecializarePOJO[].class);


                            List<String> listaSpecializari = new ArrayList<String>();
                            listaSpecializari.add("");
                            for (SpecializarePOJO specializareProfil : specializarePojo) {
                                listaSpecializari.add(specializareProfil.getNume());

                            }

                            final Spinner spinnerProfiluri = (Spinner)findViewById(textSpecializare);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1, listaSpecializari);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerProfiluri.setAdapter(adapter);

                            Log.e("mesage", "inainte de set!!!!!" );

                            spinnerProfiluri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" +textProfilAles;
                                    textSpecializareAleasa = spinnerProfiluri.getSelectedItem().toString();
                                    Log.e("mesage", "Specializarea va fi!!!!!" +textSpecializareAleasa);
                                    getMaternaForSpecializare();
                                    getObligatoriuForSpecializare();
                                    getAlegereForSpecializare();
                                    getModernaForSpecializare();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });
                            Log.e("mesage", "dupa set!!!!!" );

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("message", "Eroaree", e);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getMaternaForSpecializare(){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getMaternaForSpecializare?specializare=" +textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la proba materna: " +response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message","Dupa mapper la materna" +probe);

                            List<String> listaMaterna = new ArrayList<String>();
                            listaMaterna.add("");
                            for (ProbaDTO probaDTO :probe.getLimbaMaterna()  ) {
                                listaMaterna.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner)findViewById(textMaterna);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1,listaMaterna );

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            Log.e("mesage", "inainte de set liceu!!!!!" );

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getMaternaForSpecializare?specializare=" +textSpecializareAleasa;;
                                    textMaternaAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba materna  va fi!!!!!" +textMaternaAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message","Eroare pt materna",e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getObligatoriuForSpecializare(){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getObligatoriuForSpecializare?specializare=" +textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la proba obligatorie: " +response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message","Dupa mapper la obligatoriu" +probe);

                            List<String> listaObligatoriu = new ArrayList<String>();
                            listaObligatoriu.add("");
                            for (ProbaDTO probaDTO :probe.getProbaObligatorie()  ) {
                                listaObligatoriu.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner)findViewById(textObligatorie);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1,listaObligatoriu );

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getObligatoriuForSpecializare?specializare=" +textSpecializareAleasa;;
                                    textObligatoriuAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba materna  va fi!!!!!" +textObligatoriuAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message","Eroare pt obligatoriu",e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getAlegereForSpecializare(){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getAlegereForSpecializare?specializare=" +textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la proba obligatorie: " +response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message","Dupa mapper la obligatoriu" +probe);

                            List<String> listaAlegere = new ArrayList<String>();
                            listaAlegere.add("");
                            for (ProbaDTO probaDTO :probe.getProbaAlegere()  ) {
                                listaAlegere.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner)findViewById(textAlegere);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1,listaAlegere );

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getAlegereForSpecializare?specializare=" +textSpecializareAleasa;;
                                    textAlegereAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba alegere  va fi!!!!!" +textAlegereAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message","Eroare pt alegere",e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }
    public void getModernaForSpecializare(){


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getModernaForSpecializare?specializare=" +textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la moderna: " +response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message","Dupa mapper la moderna" +probe);

                            List<String> listaModerna = new ArrayList<String>();
                            listaModerna.add("");
                            for (ProbaDTO probaDTO :probe.getLimbaModerna()  ) {
                                listaModerna.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner)findViewById(textModerna);

                            ArrayAdapter adapter = new ArrayAdapter<String>(AdaugareElev.this,
                                    android.R.layout.simple_list_item_1,listaModerna );

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getModernaForSpecializare?specializare=" +textSpecializareAleasa;;
                                    textModernaAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "moderna  va fi!!!!!" +textModernaAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message","Eroare pt obligatoriu",e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }


}
