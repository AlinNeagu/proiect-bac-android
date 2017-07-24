package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iulia.popa on 5/30/2017.
 */

public class LiceuActivity extends MainActivity {

    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;

    public static LiceePOJO liceePojo = new LiceePOJO();
    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();
    Button button;
    ListView listView;
    ArrayAdapter adapter;
    List<ProfilPOJO> list;
    String judet = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licee);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.angry_btn);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(LiceuActivity.this,
                        AdaugaLiceuActivity.class);
                startActivity(myIntent);
            }
        });

        getLicee();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.contextmenu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Intent myIntent;
        switch (item.getItemId()) {
            case R.id.edit:
                int position1 = info.position;
                String nume1 = (String) listView.getItemAtPosition(position1);
                Integer id = 0;
               // showEditDialog(nume1);
                for (LiceuPOJO liceuPojo : liceePojo.getLicee()) {
                    if (nume1.equals(liceuPojo.getNume())){
                        judet = liceuPojo.getJudet();
                        id = liceuPojo.getId();
                        break;
                    }
                }
                myIntent = new Intent(this,EditLiceuActivity.class);
                Bundle b = new Bundle();
                b.putString("nume",nume1);
                b.putString("judet",judet);
                b.putString("id",id.toString());
                myIntent.putExtra("date",b);
                startActivity(myIntent);
                finish();
                return true;
            case R.id.delete:
                int position = info.position;
                String nume = (String) listView.getItemAtPosition(position);
                deleteLiceu(position,nume);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
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
            case R.id.action_adaugaLiceu:
                myIntent = new Intent(this,AdaugaLiceuActivity.class);
                startActivity(myIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getLicee() {


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/licee";

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

                            liceePojo = objMapper.readValue(response, LiceePOJO.class);


                            String[] listaLicee = new String[liceePojo.getLicee().size()];
                            int i = 0;
                            for (LiceuPOJO liceuPojo : liceePojo.getLicee()) {
                                listaLicee[i] = liceuPojo.getNume();
                                i++;
                            }

                            adapter = new ArrayAdapter<String>(LiceuActivity.this,
                                    R.layout.simplerow, listaLicee);

                            listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);
                            registerForContextMenu(listView);
                            listView.setOnItemSelectedListener(new OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String liceuSelectat = parent.getItemAtPosition(position).toString();

                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, liceuSelectat, Toast.LENGTH_LONG);
                                    toast.show();
                                    if (liceuSelectat.equals(liceuSelectat)) {
                                        // do your stuff
                                        getProfilForLiceu(liceuSelectat);
                                    }
                                } // to close the onItemSelected

                                public void onNothingSelected(AdapterView<?> parent) {
                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, "Nu ati selectat un liceu!", Toast.LENGTH_LONG);
                                    toast.show();
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
                Toast toast = Toast.makeText(context, "error", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


    public void deleteLiceu(int position, String nume) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Ai selectat liceul cu id = " + position, Toast.LENGTH_LONG);
        toast.show();

        int idLiceu = 0;
        for (LiceuPOJO liceuPojo : liceePojo.getLicee()) {
            if (nume.equals(liceuPojo.getNume()))
                idLiceu = liceuPojo.getId();
        }

        Context context1 = getApplicationContext();
        Toast toast1 = Toast.makeText(context1, "Se sterge liceul cu id = " + idLiceu, Toast.LENGTH_LONG);
        toast1.show();
        /*LiceuPOJO liceu = new LiceuPOJO();
        liceu.setId(id);
        JSONObject data = new JSONObject();
        try {
            data.put("id", liceu.getId());

        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        String url = "http://10.0.2.2:8080/ProiectBAC/deleteLiceu?id=" + idLiceu;

        // Request a string response from the provided URL.
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (idLiceu != 0) {

            Request stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, "stergere", Toast.LENGTH_LONG);
                                toast.show();
                                getLicee();
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
        else
        {
            Context context2 = getApplicationContext();
            Toast toast2 = Toast.makeText(context2, "id = 0", Toast.LENGTH_LONG);
            toast2.show();
            pDialog.hide();
        }
    }




    /*public  void showEditDialog(String nume){



        *//*LiceuPOJO liceu = new LiceuPOJO();
        List<Integer> profiluri = new ArrayList();
        for (Integer profil: liceu.getIdProfilInt()){
            profiluri.add(profil);
        }*//*

        List<ProfilPOJO> profiluriForLiceu = new ArrayList<>();

        FragmentManager fm = getSupportFragmentManager();

        EditLiceuActivity editNameDialogFragment = EditLiceuActivity.newInstance(nume,judet);

        editNameDialogFragment.show(fm,"activity_edit_liceu");
    }*/


    public List<ProfilPOJO> getProfilForLiceu(final String nume) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluriforUnitate?unitate=" + nume;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in getSpecializare", Toast.LENGTH_LONG);
                            toast.show();

                            profiluriPOJO = objMapper.readValue(response, new TypeReference<List<ProfilPOJO>>() {});
                            String[] listaProfiluri = new String[profiluriPOJO.size()];

                            int i = 0;
                            for (ProfilPOJO profilPOJO : profiluriPOJO) {
                                listaProfiluri[i] = profilPOJO.getDenumireProfil();
                                i++;
                            }

                            /*ArrayAdapter adapter = new ArrayAdapter<String>(LiceuActivity.this,
                                    R.layout.simplerow, listaProfiluri);

                            ListView listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);*/
                        } catch (Exception e) {

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

        return profiluriPOJO;
    }



}



