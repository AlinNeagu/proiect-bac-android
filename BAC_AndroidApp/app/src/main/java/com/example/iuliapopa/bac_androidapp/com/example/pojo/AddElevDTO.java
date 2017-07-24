package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class AddElevDTO {
	@JsonProperty("denumireUnitate")
	String denumireUnitate;
	@JsonProperty("denumireSpecializare")
	String denumireSpecializare;
	@JsonProperty("numeElev")
	String numeElev;
	@JsonProperty("prenumeElev")
	String prenumeElev;
	@JsonProperty("initialaTata")
	String initialaTata;
	@JsonProperty("probe")
	List<ProbaDTO> probe;
	public String getDenumireUnitate() {
		return denumireUnitate;
	}
	public void setDenumireUnitate(String denumireUnitate) {
		this.denumireUnitate = denumireUnitate;
	}
	public String getDenumireSpecializare() {
		return denumireSpecializare;
	}
	public void setDenumireSpecializare(String denumireSpecializare) {
		this.denumireSpecializare = denumireSpecializare;
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
	public List<ProbaDTO> getProbe() {
		return probe;
	}
	public void setProbe(List<ProbaDTO> probe) {
		this.probe = probe;
	}

	@Override
	public String toString() {
		return "AddElevDTO{" +
				"denumireUnitate='" + denumireUnitate + '\'' +
				", denumireSpecializare='" + denumireSpecializare + '\'' +
				", numeElev='" + numeElev + '\'' +
				", prenumeElev='" + prenumeElev + '\'' +
				", initialaTata='" + initialaTata + '\'' +
				", probe=" + probe +
				'}';
	}
}
