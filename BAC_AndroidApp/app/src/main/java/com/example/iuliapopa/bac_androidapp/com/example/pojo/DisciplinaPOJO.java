package com.example.iuliapopa.bac_androidapp.com.example.pojo;

/**
 * Created by iulia.popa on 5/31/2017.
 */

public class DisciplinaPOJO {

    private Integer idDisciplina;
    private String denumireDisciplina;

    public Integer getIdDisciplina() {
        return idDisciplina;
    }
    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }
    public String getDenumireDisciplina() {
        return denumireDisciplina;
    }
    public void setDenumireDisciplina(String denumireDisciplina) {
        this.denumireDisciplina = denumireDisciplina;
    }

    @Override
    public String toString() {
        return "DisciplinaPOJO [idDisciplina=" + idDisciplina + ", denumireDisciplina=" + denumireDisciplina + "]";
    }

}
