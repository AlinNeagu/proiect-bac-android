package com.example.iuliapopa.bac_androidapp.com.example.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ElevDTO;
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.LiceePOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProbePOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ProfilPOJO;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.SpecializarePOJO;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

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
        //Intent intent=getIntent();
        // id= intent.getIntExtra("id",0);
        // getDateElev(id);
    }

    public void getDateElev(int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getElevById";
        url=url.concat("/?id="+Integer.toString(id));
        Context ctx=getApplicationContext();
        Toast toast=Toast.makeText(ctx,url,Toast.LENGTH_LONG);
        toast.show();
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
        EditText tvNotaModerna=(EditText) this.findViewById(R.id.textNotaModerna);
        TextView tvElev=(TextView)this.findViewById(R.id.elev);
        Spinner tvUnitate=(Spinner) this.findViewById(R.id.textLiceu);
        Spinner tvSpecializare=(Spinner)this.findViewById(R.id.textSpecializare);
        EditText tvNotaMaterna=(EditText) this.findViewById(R.id.textNotaMaterna);
        EditText tvNotaObligatorie=(EditText)this.findViewById(R.id.textNotaObligatorie);
        EditText tvNotaAlegere=(EditText) this.findViewById(R.id.textNotaAlegere);
        EditText tvContObligatorie=(EditText) this.findViewById(R.id.textContestatieObligatorie);
        EditText tvContMaterna=(EditText) this.findViewById(R.id.textContestatieMaterna);
        EditText tvContAlegere=(EditText) this.findViewById(R.id.textContestatieAlegere);
        Spinner tvObligatorie=(Spinner) this.findViewById(R.id.textObligatorie);
        Spinner tvAlegere=(Spinner) this.findViewById(R.id.textAlegere);
        Spinner tvMaterna=(Spinner) this.findViewById(R.id.textMaterna);
        Spinner tvModerna=(Spinner) this.findViewById(R.id.textModerna);
        //TextView tvTotal=(TextView)this.findViewById(R.id.total);
        EditText numeElev=(EditText) this.findViewById(R.id.textNumeIn);
        EditText prenumeElev=(EditText) this.findViewById(R.id.textPrenumeIn);
        EditText initialaTata=(EditText) this.findViewById(R.id.textInitialaIn);

        // tvAlegere.setText(elev.getProbaAlegere().getNume());
        //  tvObligatorie.setText(elev.getProbaObligatorie().getNume());
        // tvMaterna.setText(elev.getLimbaMaterna().getNume());
        // tvModerna.setText(elev.getLimbaModerna().getNume());


        //tvElev.setText(elev.getNume()+" "+elev.getInitialaTatalui()+" "+elev.getPrenume());
        // tvSpecializare.setText(elev.getSpec().getNume());
        //  tvUnitate.setText(elev.getLiceu().getNume());
        numeElev.setText(String.valueOf(elev.getNume()));
        prenumeElev.setText(String.valueOf(elev.getPrenume()));
        initialaTata.setText(String.valueOf(elev.getInitialaTatalui()));

        tvNotaModerna.setText(String.valueOf(elev.getLimbaModerna().getNota()));
        tvNotaAlegere.setText(String.valueOf(elev.getProbaAlegere().getNota()));
        tvNotaMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNota()));
        tvNotaObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNota()));

        tvContAlegere.setText(String.valueOf(elev.getProbaAlegere().getNotaContestatie()));
        tvContMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNotaContestatie()));
        tvContObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNotaContestatie()));



    }

}

