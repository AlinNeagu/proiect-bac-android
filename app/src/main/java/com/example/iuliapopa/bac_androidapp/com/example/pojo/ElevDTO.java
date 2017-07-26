package com.example.iuliapopa.bac_androidapp.com.example.pojo;

public class ElevDTO {
    private Integer id;
    private String nume;
    private String prenume;
    private String initialaTatalui;

    private LiceuPOJO liceu;
    private ProfilPOJO profil;
    private SpecializarePOJO spec;
    private ProbaDTO limbaMaterna;
    private ProbaDTO limbaModerna;
    private ProbaDTO probaAlegere;
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

}