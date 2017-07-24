package com.example.iuliapopa.bac_androidapp.com.example.pojo;

public class ProbaDTO {
    Integer id;
    String nume;
    Double nota;
    Double notaContestatie;
    Double notaFinala;
    Integer probaId;


    public Double getNotaFinala() {
        return notaFinala;
    }
    public void setNotaFinala(Double notaFinala) {
        this.notaFinala = notaFinala;
    }
    public Integer getProbaId() {
        return probaId;
    }
    public void setProbaId(Integer probaId) {
        this.probaId = probaId;
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
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public Double getNotaContestatie() {
        return notaContestatie;
    }
    public void setNotaContestatie(Double notaContestatie) {
        this.notaContestatie = notaContestatie;
    }
    @Override
    public String toString() {
        return "ProbaDTO [id=" + id + ", nume=" + nume + ", nota=" + nota + ", notaContestatie=" + notaContestatie
                + "]";
    }

}