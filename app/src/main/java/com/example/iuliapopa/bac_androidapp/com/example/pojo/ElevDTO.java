package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

public class ElevDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nume")
    private String nume;
    @JsonProperty("prenume")
    private String prenume;
    @JsonProperty("initialaTatalui")
    private String initialaTatalui;
    @JsonProperty("liceu")
    private LiceuPOJO liceu;
    @JsonProperty("profil")
    private ProfilPOJO profil;
    @JsonProperty("spec")
    private SpecializarePOJO spec;
    @JsonProperty("limbaMaterna")
    private ProbaDTO limbaMaterna;
    @JsonProperty("limbaModerna")
    private ProbaDTO limbaModerna;
    @JsonProperty("probaAlegere")
    private ProbaDTO probaAlegere;
    @JsonProperty("probaObligatorie")
    private ProbaDTO probaObligatorie;
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
    public String getPrenume() {
        return prenume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public String getInitialaTatalui() {
        return initialaTatalui;
    }
    public void setInitialaTatalui(String initialaTatalui) {
        this.initialaTatalui = initialaTatalui;
    }
    public LiceuPOJO getLiceu() {
        return liceu;
    }
    public void setLiceu(LiceuPOJO liceu) {
        this.liceu = liceu;
    }
    public ProfilPOJO getProfil() {
        return profil;
    }
    public void setProfil(ProfilPOJO profil) {
        this.profil = profil;
    }
    public SpecializarePOJO getSpec() {
        return spec;
    }
    public void setSpec(SpecializarePOJO spec) {
        this.spec = spec;
    }
    public ProbaDTO getLimbaMaterna() {
        return limbaMaterna;
    }
    public void setLimbaMaterna(ProbaDTO limbaMaterna) {
        this.limbaMaterna = limbaMaterna;
    }
    public ProbaDTO getLimbaModerna() {
        return limbaModerna;
    }
    public void setLimbaModerna(ProbaDTO limbaModerna) {
        this.limbaModerna = limbaModerna;
    }
    public ProbaDTO getProbaAlegere() {
        return probaAlegere;
    }
    public void setProbaAlegere(ProbaDTO probaAlegere) {
        this.probaAlegere = probaAlegere;
    }
    public ProbaDTO getProbaObligatorie() {
        return probaObligatorie;
    }
    public void setProbaObligatorie(ProbaDTO probaObligatorie) {
        this.probaObligatorie = probaObligatorie;
    }

    @Override
    public String toString() {
        return "ElevDTO{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", initialaTatalui='" + initialaTatalui + '\'' +
                ", liceu=" + liceu +
                ", profil=" + profil +
                ", spec=" + spec +
                ", limbaMaterna=" + limbaMaterna +
                ", limbaModerna=" + limbaModerna +
                ", probaAlegere=" + probaAlegere +
                ", probaObligatorie=" + probaObligatorie +
                '}';
    }
}