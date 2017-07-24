package com.example.iuliapopa.bac_androidapp.com.example.pojo;

/**
 * Created by iulia.popa on 6/6/2017.
 */

public class SpecializarePOJO {

    private Integer id;
    private String nume;
    private ProfilPOJO profil;


    public ProfilPOJO getProfil() {
        return profil;
    }

    public void setProfil(ProfilPOJO profil) {
        this.profil = profil;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SpecializarePOJO{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", profil=" + profil +
                '}';
    }
}
