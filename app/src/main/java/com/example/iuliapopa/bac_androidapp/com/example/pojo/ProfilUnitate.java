package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import java.util.List;

/**
 * Created by Iulia.Popa on 7/7/2017.
 */

public class ProfilUnitate {


    String nume;
    List<ProfilPOJO> listaProfiluri;

    public ProfilUnitate(String nume, List<ProfilPOJO> listaProfiluri){
        this.nume = nume;
        this.listaProfiluri = listaProfiluri;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<ProfilPOJO> getListaProfiluri() {
        return listaProfiluri;
    }

    public void setListaProfiluri(List<ProfilPOJO> listaProfiluri) {
        this.listaProfiluri = listaProfiluri;
    }
}
