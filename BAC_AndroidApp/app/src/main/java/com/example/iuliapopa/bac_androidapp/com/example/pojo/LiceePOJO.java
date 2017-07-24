package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import java.util.List;

/**
 * Created by iulia.popa on 5/30/2017.
 */

public class LiceePOJO {

    List<LiceuPOJO> licee;

    public List<LiceuPOJO> getLicee() {
        return licee;
    }


    public void setLicee(List<LiceuPOJO> licee) {
        this.licee = licee;
    }


    @Override
    public String toString() {
        return "LiceuPOJO [licee=" + licee + "]";
    }
}
