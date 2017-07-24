package com.example.iuliapopa.bac_androidapp.com.example.pojo;

/**
 * Created by iulia.popa on 5/30/2017.
 */

import java.util.List;


public class LiceuPOJO {


    private Integer id;
    private String nume;;
    private String judet;

    List<ProfilPOJO> idProfil;
    List<Integer> idProfilInt;


    public List<Integer> getIdProfilInt() {
        return idProfilInt;
    }
    public void setIdProfilInt(List<Integer> idProfilInt) {
        this.idProfilInt = idProfilInt;
    }
    public List<ProfilPOJO> getIdProfil() {
        return idProfil;
    }
    public void setIdProfil(List<ProfilPOJO> idProfil) {
        this.idProfil = idProfil;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getJudet() {
        return judet;
    }
    public void setJudet(String judet) {
        this.judet = judet;
    }

}