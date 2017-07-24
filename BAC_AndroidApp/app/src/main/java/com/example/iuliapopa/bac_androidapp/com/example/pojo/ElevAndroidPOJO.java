package com.example.iuliapopa.bac_androidapp.com.example.pojo;

/**
 * Created by Ioana.Nenciu on 23.05.2017.
 */

public class ElevAndroidPOJO {

    private Integer id;
    private String numeElev;
    private String prenumeElev;
    private String initialaTata;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNumeElev() {
        return numeElev;
    }
    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }
    public String getPrenumeElev() {
        return prenumeElev;
    }
    public void setPrenumeElev(String prenumeElev) {
        this.prenumeElev = prenumeElev;
    }
    public String getInitialaTata() {
        return initialaTata;
    }
    public void setInitialaTata(String initialaTata) {
        this.initialaTata = initialaTata;
    }
    @Override
    public String toString() {
        return "ElevAndroidPOJO [id=" + id + ", numeElev=" + numeElev + ", prenumeElev=" + prenumeElev
                + ", initialaTata=" + initialaTata + "]";
    }

}


