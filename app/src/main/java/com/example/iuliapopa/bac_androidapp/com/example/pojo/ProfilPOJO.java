package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import java.util.List;

/**
 * Created by iulia.popa on 5/31/2017.
 */

public class ProfilPOJO {

    private String denumireProfil;

    private Integer idProfil;


    private Integer currentId;
    List<String> specializari;



    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    public List<String> getSpecializari() {
        return specializari;
    }

    public void setSpecializari(List<String> specializari) {
        this.specializari = specializari;
    }

    public Integer getIdProfil() {
        return idProfil;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public String getDenumireProfil() {
        return denumireProfil;
    }

    public void setDenumireProfil(String denumireProfil) {
        this.denumireProfil = denumireProfil;
    }
}
