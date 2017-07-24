package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import java.util.List;

/**
 * Created by Ioana.Nenciu on 23.05.2017.
 */

public class EleviAndroidPOJO {
    List<ElevAndroidPOJO> elevi;

    public List<ElevAndroidPOJO> getElevi() {
        return elevi;
    }


    public void setElevi(List<ElevAndroidPOJO> elevi) {
        this.elevi = elevi;
    }


    @Override
    public String toString() {
        return "EleviAndroidPOJO [elevi=" + elevi + "]";
    }


}
