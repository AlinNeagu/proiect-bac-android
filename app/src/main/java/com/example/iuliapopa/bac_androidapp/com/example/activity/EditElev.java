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
import com.example.iuliapopa.bac_androidapp.com.example.pojo.AddElevDTO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ElevDTO;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceePOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceuPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProbaDTO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProbePOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.SpecializarePOJO;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.iuliapopa.bac_androidapp.R.id.textAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textAlegere2;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieAlegere2;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieMaterna2;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textContestatieObligatorie2;
import static com.example.iuliapopa.bac_androidapp.R.id.textInitialaIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textInitialaIn2;
import static com.example.iuliapopa.bac_androidapp.R.id.textLiceu;
import static com.example.iuliapopa.bac_androidapp.R.id.textLiceu2;
import static com.example.iuliapopa.bac_androidapp.R.id.textMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textMaterna2;
import static com.example.iuliapopa.bac_androidapp.R.id.textModerna;
import static com.example.iuliapopa.bac_androidapp.R.id.textModerna2;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaAlegere;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaAlegere2;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaMaterna;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaMaterna2;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaModerna;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaModerna2;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textNotaObligatorie2;
import static com.example.iuliapopa.bac_androidapp.R.id.textNumeIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textNumeIn2;
import static com.example.iuliapopa.bac_androidapp.R.id.textObligatorie;
import static com.example.iuliapopa.bac_androidapp.R.id.textObligatorie2;
import static com.example.iuliapopa.bac_androidapp.R.id.textPrenumeIn;
import static com.example.iuliapopa.bac_androidapp.R.id.textPrenumeIn2;
import static com.example.iuliapopa.bac_androidapp.R.id.textProfil;
import static com.example.iuliapopa.bac_androidapp.R.id.textProfil2;
import static com.example.iuliapopa.bac_androidapp.R.id.textSpecializare;
import static com.example.iuliapopa.bac_androidapp.R.id.textSpecializare2;

public class EditElev extends AppCompatActivity {

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

    private ElevDTO elev;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_elev);
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

        Intent intent = getIntent();
        id = intent.getIntExtra("idElev", 0);
        getDateElev2(id);
        getNumeLicee();

        Button clickButton = (Button) findViewById(R.id.editareElev);
        clickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("message", "Ai apasat pe buton");
                editElev();


                // goToMain();

            }


        });
    }


    public void editElev() {

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        ElevDTO elev = null;
        String elevSerialized = "";
        try {

            ProbaDTO probaMaterna = new ProbaDTO();
            ProbaDTO probaObligatorie = new ProbaDTO();
            ProbaDTO probaAlegere = new ProbaDTO();
            ProbaDTO probaModerna = new ProbaDTO();
            LiceuPOJO liceu = new LiceuPOJO();
            SpecializarePOJO spec = new SpecializarePOJO();
            EditText numeElev = (EditText) findViewById(textNumeIn2);
            textNumeElev = numeElev.getText().toString();
            Log.e("message", "numele este: " + textNumeElev);
            EditText prenumeElev = (EditText) findViewById(textPrenumeIn2);
            textPrenumeElev = prenumeElev.getText().toString();
            Log.e("message", "prenume este: " + textPrenumeElev);
            EditText initialaTata = (EditText) findViewById(textInitialaIn2);
            textInitialaTata = initialaTata.getText().toString();
            Log.e("message", "initiala este: " + textInitialaTata);
            Log.e("message", "liceu este: " + textLiceuAles);
            Log.e("message", "profil este: " + textProfilAles);
            Log.e("message", "spec este: " + textSpecializareAleasa);
            Log.e("message", "materna este: " + textMaternaAleasa);
            EditText textMaternaNota = (EditText) findViewById(textNotaMaterna2);
            Log.e("message", "hello, ");
            textNotaMaternaa = Double.parseDouble(textMaternaNota.getText().toString());
            Log.e("'message", "notamaterna" + textNotaMaternaa);
            EditText textMaternaContestatie = (EditText) findViewById(textContestatieMaterna2);
            textNotaContestatieMaternaa = Double.parseDouble(textMaternaContestatie.getText().toString());
            Log.e("message", "obligatoriu este: " + textObligatoriuAleasa);
            EditText textObligatoriuNota = (EditText) findViewById(textNotaObligatorie2);
            textNotaObligatoriuu = Double.parseDouble(textObligatoriuNota.getText().toString());
            EditText textObligatoriuContestatie = (EditText) findViewById(textContestatieObligatorie2);
            textNotaContestatieObligatoriuu = Double.parseDouble(textObligatoriuContestatie.getText().toString());
            Log.e("message", "alegere este: " + textAlegereAleasa);
            EditText alegereNota = (EditText) findViewById(textNotaAlegere2);
            textNotaAlegeree = Double.parseDouble(alegereNota.getText().toString());
            EditText alegereContestatie = (EditText) findViewById(textContestatieAlegere2);
            textNotaContestatieAlegeree = Double.parseDouble(alegereContestatie.getText().toString());
            Log.e("message", "moderna este" + textModernaAleasa);
            EditText modernaNota = (EditText) findViewById(textNotaModerna2);
            textNotaModernaa = Double.parseDouble(modernaNota.getText().toString());
            // elev.setNumeElev(numeElev.getText().toString());
            Log.e("message", "numele NENUL este!!! " + textNumeElev);
            elev = new ElevDTO();
            elev.setId(id);
            elev.setNume(textNumeElev);
            elev.setPrenume(textPrenumeElev);
            elev.setInitialaTatalui(textInitialaTata);
            liceu.setNume(textLiceuAles);
            elev.setLiceu(liceu);
            spec.setNume(textSpecializareAleasa);
            elev.setSpec(spec);

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

            // List<ProbaDTO> probe = new ArrayList<ProbaDTO>();
            elev.setLimbaMaterna(probaMaterna);
            elev.setProbaObligatorie(probaObligatorie);
            elev.setProbaAlegere(probaAlegere);
            elev.setLimbaModerna(probaModerna);

            //elev.setProbe(probe);
            Log.e("message", "Elevul este: " + elev);


            ObjectMapper objMapper = new ObjectMapper();
            elevSerialized = objMapper.writeValueAsString(elev);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("message", "Eroaree", e);

        }


        String url = "http://10.0.2.2:8080/ProiectBAC/updateElev?elev=" + elevSerialized;

        Request stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "Elevul a fost editat!", Toast.LENGTH_LONG);
                            toast.show();
                            goToMain();
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
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Error", Toast.LENGTH_LONG);
                toast.show();
            }
        };

        queue.add(stringRequest);
    }


    void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void getDateElev2(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getElevById";
        url=url.concat("/?id="+Integer.toString(id));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper om=new ObjectMapper();
                        try {
                            elev=om.readValue(response,ElevDTO.class);
                            afiseazaDate();
                        } catch (IOException e) {
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
        queue.add(stringRequest);
    }
    public void afiseazaDate(){
        EditText EDnumeElev = (EditText) this.findViewById(textNumeIn2);
        EditText EDprenumeElev = (EditText) this.findViewById(textPrenumeIn2);
        EditText EDinitialaTata = (EditText) this.findViewById(textInitialaIn2);
        EditText EDNotaModerna = (EditText) this.findViewById(textNotaModerna2);
        // TextView tvElev=(TextView)this.findViewById(R.id.elev);
        Spinner EDProfil = (Spinner) this.findViewById(textProfil2);
        Spinner EDUnitate = (Spinner) this.findViewById(textLiceu2);
        Spinner EDSpecializare = (Spinner) this.findViewById(textSpecializare2);
        EditText EDNotaMaterna = (EditText) this.findViewById(textNotaMaterna2);
        EditText EDNotaObligatorie = (EditText) this.findViewById(textNotaObligatorie2);
        EditText EDNotaAlegere = (EditText) this.findViewById(textNotaAlegere2);
        EditText EDContObligatorie = (EditText) this.findViewById(textContestatieObligatorie2);
        EditText EDContMaterna = (EditText) this.findViewById(textContestatieMaterna2);
        EditText EDContAlegere = (EditText) this.findViewById(textContestatieAlegere2);
        Spinner EDObligatorie = (Spinner) this.findViewById(textObligatorie2);
        Spinner EDAlegere = (Spinner) this.findViewById(textAlegere2);
        Spinner EDMaterna = (Spinner) this.findViewById(textMaterna2);
        Spinner EDModerna = (Spinner) this.findViewById(textModerna2);
        //TextView tvTotal=(TextView)this.findViewById(R.id.total);


        // EDAlegere.setText(String.valueOf(elev.getProbaAlegere().getNume()));
        //  EDObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNume());
        // EDMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNume()));
        // EDModerna.setText(String.valueOf(elev.getLimbaModerna().getNume());


        //tvElev.setText(elev.getNume()+" "+elev.getInitialaTatalui()+" "+elev.getPrenume());
        // EDSpecializare.setText(String.valueOf(elev.getSpec().getNume()));
        //  EDUnitate.setPrompt(String.valueOf(elev.getLiceu().getNume()));
        // EDProfil.setPrompt(String.valueOf(elev.getProfil().getDenumireProfil()));
        EDnumeElev.setText(String.valueOf(elev.getNume()));
        EDprenumeElev.setText(String.valueOf(elev.getPrenume()));
        EDinitialaTata.setText(String.valueOf(elev.getInitialaTatalui()));

        EDNotaModerna.setText(String.valueOf(elev.getLimbaModerna().getNota()));
        EDNotaAlegere.setText(String.valueOf(elev.getProbaAlegere().getNota()));
        EDNotaMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNota()));
        EDNotaObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNota()));

        EDContAlegere.setText(String.valueOf(elev.getProbaAlegere().getNotaContestatie()));
        EDContMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNotaContestatie()));
        EDContObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNotaContestatie()));



    }

    public void getNumeLicee() {


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
                            Log.e("message", "raspunsul de la licee: " + response);
                            Log.e("message", "Numele liceului default: " + String.valueOf(elev.getLiceu().getNume()));
                            liceePojo = objMapper.readValue(response, LiceePOJO.class);


                            List<String> listaLicee = new ArrayList<String>();
                            listaLicee.add(String.valueOf(elev.getLiceu().getNume()));

                            for (LiceuPOJO liceuPojo : liceePojo.getLicee()) {
                                // UnitateDeInvatamant unitate = new UnitateDeInvatamant();
                                // unitate.setIdUnitate(liceuPojo.getId());
                                // unitate.setDenumireUnitate(liceuPojo.getNume());
                                listaLicee.add(liceuPojo.getNume());

                            }


                            final Spinner spinnerLicee = (Spinner) findViewById(textLiceu2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaLicee);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            Log.e("mesage", "inainte de set liceu!!!!!");

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/numeLicee";
                                    textLiceuAles = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "liceul va fi!!!!!" + textLiceuAles);
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
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`tttt work for licee", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        queue.add(stringRequest);
    }

    public void getProfiluriForUnitate() {
        Log.e("message", "############Liceul ales " + textLiceuAles);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriForUnitate?unitate=" + textLiceuAles;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseTrial) {
                        // Display the response string.
                        try {

                            Log.e("message", "profil trial: " + responseTrial);
                            profilPojo = objMapper.readValue(responseTrial, ProfilPOJO[].class);
                            Log.e("message", "dupa mapper: ");

                            List<String> listaProfiluri = new ArrayList<String>();
                            listaProfiluri.add(String.valueOf(elev.getProfil().getDenumireProfil()));
                            for (ProfilPOJO profilLiceu : profilPojo) {
                                listaProfiluri.add(profilLiceu.getDenumireProfil());

                            }

                            final Spinner spinnerProfiluri = (Spinner) findViewById(textProfil2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaProfiluri);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerProfiluri.setAdapter(adapter);

                            Log.e("mesage", "inainte de set!!!!!");

                            spinnerProfiluri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriForUnitate?unitate=" + textLiceuAles;
                                    textProfilAles = spinnerProfiluri.getSelectedItem().toString();
                                    Log.e("mesage", "profilul va fi!!!!!" + textProfilAles);
                                    getSpecializariForProfil();
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });
                            Log.e("mesage", "dupa set!!!!!");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("message", "Eroaree", e);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`tttt work for profiluri TRIAL", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        queue.add(stringRequest);
    }

    public void getSpecializariForProfil() {
        Log.e("message", "############Profilul ales " + textProfilAles);
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" + textProfilAles;


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responseTrial) {
                        // Display the response string.
                        try {

                            Log.e("message", "Specializare: " + responseTrial);
                            specializarePojo = objMapper.readValue(responseTrial, SpecializarePOJO[].class);


                            List<String> listaSpecializari = new ArrayList<String>();
                            listaSpecializari.add(String.valueOf(elev.getSpec().getNume()));
                            for (SpecializarePOJO specializareProfil : specializarePojo) {
                                listaSpecializari.add(specializareProfil.getNume());

                            }

                            final Spinner spinnerProfiluri = (Spinner) findViewById(textSpecializare2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaSpecializari);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerProfiluri.setAdapter(adapter);

                            Log.e("mesage", "inainte de set!!!!!");

                            spinnerProfiluri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" + textProfilAles;
                                    textSpecializareAleasa = spinnerProfiluri.getSelectedItem().toString();
                                    Log.e("mesage", "Specializarea va fi!!!!!" + textSpecializareAleasa);
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
                            Log.e("mesage", "dupa set!!!!!");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("message", "Eroaree", e);

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("message", "Eroare profiluri");

            }
        });

        queue.add(stringRequest);
    }

    public void getMaternaForSpecializare() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getMaternaForSpecializare?specializare=" + textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la proba materna: " + response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message", "Dupa mapper la materna" + probe);

                            List<String> listaMaterna = new ArrayList<String>();
                            listaMaterna.add(String.valueOf(elev.getLimbaMaterna().getNume()));
                            for (ProbaDTO probaDTO : probe.getLimbaMaterna()) {
                                listaMaterna.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner) findViewById(textMaterna2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaMaterna);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            Log.e("mesage", "inainte de set liceu!!!!!");

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getMaternaForSpecializare?specializare=" + textSpecializareAleasa;
                                    ;
                                    textMaternaAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba materna  va fi!!!!!" + textMaternaAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message", "Eroare pt materna", e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "doesn`tttt work for licee", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        queue.add(stringRequest);
    }

    public void getObligatoriuForSpecializare() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getObligatoriuForSpecializare?specializare=" + textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la proba obligatorie: " + response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message", "Dupa mapper la obligatoriu" + probe);

                            List<String> listaObligatoriu = new ArrayList<String>();
                            listaObligatoriu.add(String.valueOf(elev.getProbaObligatorie().getNume()));
                            for (ProbaDTO probaDTO : probe.getProbaObligatorie()) {
                                listaObligatoriu.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner) findViewById(textObligatorie2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaObligatoriu);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getObligatoriuForSpecializare?specializare=" + textSpecializareAleasa;
                                    ;
                                    textObligatoriuAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba materna  va fi!!!!!" + textObligatoriuAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message", "Eroare pt obligatoriu", e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("message", "Eroare obligatorie");
            }
        });

        queue.add(stringRequest);
    }

    public void getAlegereForSpecializare() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getAlegereForSpecializare?specializare=" + textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {
                            Log.e("message", "raspunsul de la proba obligatorie: " + response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message", "Dupa mapper la obligatoriu" + probe);

                            List<String> listaAlegere = new ArrayList<String>();
                            listaAlegere.add(String.valueOf(elev.getProbaAlegere().getNume()));
                            for (ProbaDTO probaDTO : probe.getProbaAlegere()) {
                                listaAlegere.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner) findViewById(textAlegere2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaAlegere);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getAlegereForSpecializare?specializare=" + textSpecializareAleasa;
                                    ;
                                    textAlegereAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "proba alegere  va fi!!!!!" + textAlegereAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message", "Eroare pt alegere", e);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void getModernaForSpecializare() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getModernaForSpecializare?specializare=" + textSpecializareAleasa;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        try {

                            Log.e("message", "raspunsul de la moderna: " + response);
                            probe = objMapper.readValue(response, ProbePOJO.class);
                            Log.e("message", "Dupa mapper la moderna" + probe);

                            List<String> listaModerna = new ArrayList<String>();
                            listaModerna.add(String.valueOf(elev.getLimbaModerna().getNume()));
                            for (ProbaDTO probaDTO : probe.getLimbaModerna()) {
                                listaModerna.add(probaDTO.getNume());

                            }

                            final Spinner spinnerLicee = (Spinner) findViewById(textModerna2);

                            ArrayAdapter adapter = new ArrayAdapter<String>(EditElev.this,
                                    android.R.layout.simple_list_item_1, listaModerna);

                            adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                            spinnerLicee.setAdapter(adapter);

                            spinnerLicee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                                    String url = "http://10.0.2.2:8080/ProiectBAC/getModernaForSpecializare?specializare=" + textSpecializareAleasa;
                                    ;
                                    textModernaAleasa = spinnerLicee.getSelectedItem().toString();
                                    Log.e("mesage", "moderna  va fi!!!!!" + textModernaAleasa);

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parentView) {
                                    // your code here
                                }


                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e("message", "Eroare pt obligatoriu", e);
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

