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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iulia.popa on 6/6/2017.
 */

public class SpecializareActivity extends MainActivity{

    private ListView mainListView;
    private ListView listView;
    private ArrayAdapter<String> listAdapter;
    Button button;
    List<ProfilPOJO> profiluriPOJO = new ArrayList<>();
    List<SpecializarePOJO> specializariPOJO = new ArrayList<>();
    ObjectMapper objMapper = new ObjectMapper();
    ArrayAdapter adapter;
    Spinner spinner;
    String profilSelectat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specializari);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.angry_btn);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent myIntent = new Intent(SpecializareActivity.this,
                        AdaugaSpecializare.class);
                startActivity(myIntent);
            }
        });

        getProfiluri();
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
        Intent myIntent = new Intent();
        switch (item.getItemId()) {
            case R.id.edit:
                int position1 = info.position;
                String nume1 = (String) listView.getItemAtPosition(position1);

                myIntent = new Intent(this,EditSpecializare.class);
                Bundle b = new Bundle();
                b.putString("nume",nume1);

                for (SpecializarePOJO spec: specializariPOJO){
                    if (nume1.equals(spec.getNume())){
                            b.putString("profil",profilSelectat);
                            b.putString("id",spec.getId().toString());
                        }
                    }
                myIntent.putExtra("date",b);
                startActivity(myIntent);
                finish();
                return true;
            case R.id.delete:
                int position = info.position;
                String nume = (String) listView.getItemAtPosition(position);
                deleteSpecializare(position,nume);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getProfiluri() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getProfiluri";

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


                            ArrayAdapter adapter = new ArrayAdapter<String>(SpecializareActivity.this,
                                    R.layout.simple_spinner_item, listaProfiluri);


                            spinner = (Spinner) findViewById(R.id.spinner);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new OnItemSelectedListener()
                            {
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                                {
                                    profilSelectat = parent.getItemAtPosition(position).toString();

                                    Context context = getApplicationContext();
                                    Toast toast = Toast.makeText(context, profilSelectat, Toast.LENGTH_LONG);
                                    toast.show();
                                    if(profilSelectat.equals(profilSelectat))
                                    {
                                        // do your stuff
                                        getSpecializari(profilSelectat);
                                    }
                                } // to close the onItemSelected
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

    public void getSpecializari(String profil){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getSpecializariForProfil?profil=" + profil;

        // Request a string response from the provided URL.
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context, "am intrat in getSpecializare", Toast.LENGTH_LONG);
                            toast.show();

                            specializariPOJO = objMapper.readValue(response, new TypeReference<List<SpecializarePOJO>>() {});
                            String[] listaSpecializari = new String[specializariPOJO.size()];

                            int i = 0;
                            for (SpecializarePOJO specializarePOJO : specializariPOJO) {
                                listaSpecializari[i] = specializarePOJO.getNume();
                                i++;
                            }

                            ArrayAdapter adapter = new ArrayAdapter<String>(SpecializareActivity.this,
                                    R.layout.simplerow, listaSpecializari);

                            listView = (ListView) findViewById(R.id.mainListView);
                            listView.setAdapter(adapter);
                            registerForContextMenu(listView);

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
                Toast toast = Toast.makeText(context, "ERROR", Toast.LENGTH_LONG);
                toast.show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void deleteSpecializare(int position, String nume) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Ai selectat specializarea cu id = " + position, Toast.LENGTH_LONG);
        toast.show();

        int idSpecializare = 0;
        for (SpecializarePOJO spec : specializariPOJO) {
            if (nume.equals(spec.getNume()))
                idSpecializare = spec.getId();
        }

        Context context1 = getApplicationContext();
        Toast toast1 = Toast.makeText(context1, "Se sterge specializarea cu id = " + idSpecializare, Toast.LENGTH_LONG);
        toast1.show();

        String url = "http://10.0.2.2:8080/ProiectBAC/deleteSpecializare?id=" + idSpecializare;

        // Request a string response from the provided URL.
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        if (idSpecializare != 0) {

            Request stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            try {

                                Context context = getApplicationContext();
                                Toast toast = Toast.makeText(context, "stergere", Toast.LENGTH_LONG);
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
        else
        {
            Context context2 = getApplicationContext();
            Toast toast2 = Toast.makeText(context2, "id = 0", Toast.LENGTH_LONG);
            toast2.show();
            pDialog.hide();
        }
    }

    /*http://localhost:8080/ProiectBAC/editSpecializare?specializare={"id":34,"nume":"aaaaaaaaa",
    "profil":{"denumireProfil":"Tehnologic","idProfil":3,"currentId":null,"specializari":null}}*/

}
