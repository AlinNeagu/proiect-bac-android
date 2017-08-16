package com.example.iuliapopa.bac_androidapp.com.example.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.iuliapopa.bac_androidapp.R;
import com.example.iuliapopa.bac_androidapp.com.example.pojo.ElevDTO;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Date_Elev extends AppCompatActivity {
    int id;
    int idElev;
    private ElevDTO elev = new ElevDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date__elev);
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
        Intent intent=getIntent();
        id= intent.getIntExtra("id",0);

        getDateElev(id);

        Button clickButton = (Button) findViewById(R.id.deleteElev);
        clickButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("message","Ai apasat pe butonul de stergere");
                deleteElev(id);


                goToMain();

            }


        });
        Button clickButton2 = (Button) findViewById(R.id.editElev);
        clickButton2.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("message","Ai apasat pe butonul de editare");
                Intent intent = new Intent(Date_Elev.this, EditElev.class);
                intent.putExtra("idElev", id);

                //  goToEditElev(id);
                Log.e("message", "id-ul elevului este:" + id);
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Aici poti edita elevul", Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }


        });
    }

    void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
    public void deleteElev(int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/deleteElev";
        url=url.concat("/?idElev="+Integer.toString(id));
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("message","Am ajuns in response-ul de la stergere!");
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "Elevul a fost sters!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        Context context = getApplicationContext();
        Toast toast1 = Toast.makeText(context, "Elevul a fost sters", Toast.LENGTH_LONG);
        toast1.show();
    }

    void goToEditElev(int id){
        Intent intent = new Intent(Date_Elev.this, EditElev.class);
        intent.putExtra("idElev", id);
        startActivity(intent);
        // getDateElev2(id);


    }

    public void updateView(){
        TextView tvNotaModerna=(TextView)this.findViewById(R.id.notaModerna);
        TextView tvElev=(TextView)this.findViewById(R.id.elev);
        TextView tvUnitate=(TextView)this.findViewById(R.id.unitate);
        TextView tvSpecializare=(TextView)this.findViewById(R.id.specializare);
        TextView tvNotaMaterna=(TextView)this.findViewById(R.id.notaMaterna);
        TextView tvNotaObligatorie=(TextView)this.findViewById(R.id.notaObligatorie);
        TextView tvNotaAlegere=(TextView)this.findViewById(R.id.notaAlegere);
        TextView tvContModerna=(TextView)this.findViewById(R.id.contModerna);
        TextView tvContObligatorie=(TextView)this.findViewById(R.id.contObligatorie);
        TextView tvContMaterna=(TextView)this.findViewById(R.id.contMaterna);
        TextView tvContAlegere=(TextView)this.findViewById(R.id.contAlegere);
        TextView tvFinalaModerna=(TextView)this.findViewById(R.id.finalaModerna);
        TextView tvFinalaMaterna=(TextView)this.findViewById(R.id.finaalaMaterna);
        TextView tvFinalaAlegere=(TextView)this.findViewById(R.id.finalaAlegere);
        TextView tvFinalaObligatorie=(TextView)this.findViewById(R.id.finalaObligatorie);
        TextView tvObligatorie=(TextView)this.findViewById(R.id.obligatorie);
        TextView tvAlegere=(TextView)this.findViewById(R.id.alegere);
        TextView tvMaterna=(TextView)this.findViewById(R.id.materna);
        TextView tvModerna=(TextView)this.findViewById(R.id.moderna);
        TextView tvTotal=(TextView)this.findViewById(R.id.notaFinala);
        TextView tvResult=(TextView)this.findViewById(R.id.finalResult);


        tvAlegere.setText(elev.getProbaAlegere().getNume());
        tvObligatorie.setText(elev.getProbaObligatorie().getNume());
        tvMaterna.setText(elev.getLimbaMaterna().getNume());
        tvModerna.setText(elev.getLimbaModerna().getNume());

        tvElev.setText(elev.getNume()+" "+elev.getInitialaTatalui()+" "+elev.getPrenume());
        tvSpecializare.setText(elev.getSpec().getNume());
        tvUnitate.setText(elev.getLiceu().getNume());

        tvNotaModerna.setText(String.valueOf(elev.getLimbaModerna().getNota()));
        tvNotaAlegere.setText(String.valueOf(elev.getProbaAlegere().getNota()));
        tvNotaMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNota()));
        tvNotaObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNota()));

        tvContAlegere.setText(String.valueOf(elev.getProbaAlegere().getNotaContestatie()));
        tvContMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNotaContestatie()));
        tvContModerna.setText(String.valueOf(elev.getLimbaModerna().getNotaContestatie()));
        tvContObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNotaContestatie()));


        tvFinalaAlegere.setText(String.valueOf(elev.getLimbaModerna().getNotaFinala()));
        tvFinalaMaterna.setText(String.valueOf(elev.getLimbaMaterna().getNotaFinala()));
        tvFinalaModerna.setText(String.valueOf(elev.getLimbaModerna().getNotaFinala()));
        tvFinalaObligatorie.setText(String.valueOf(elev.getProbaObligatorie().getNotaFinala()));

        Double notaFianal=(elev.getLimbaModerna().getNota()+elev.getProbaAlegere().getNota()+elev.getProbaObligatorie().getNota())/3;
        tvTotal.setText(notaFianal.toString());
        if(notaFianal>=6){
            tvResult.setText("Admis");
            tvResult.setTextColor(Color.GREEN);
        }
        else{
            tvResult.setText("Respins");
            tvResult.setTextColor(Color.RED);
        }
    }

    public void getDateElev(int id){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8080/ProiectBAC/getElevById";
        url=url.concat("/?id="+Integer.toString(id));
        //Context ctx=getApplicationContext();
        // Toast toast=Toast.makeText(ctx,url,Toast.LENGTH_LONG);
        // toast.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper om=new ObjectMapper();
                        try {
                            elev=om.readValue(response,ElevDTO.class);
                            updateView();
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

}
