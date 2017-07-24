package com.example.iuliapopa.bac_androidapp.com.example.pojo;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class ProbePOJO {

	@JsonProperty("probaObligatorie")
	List<ProbaDTO> probaObligatorie;
	@JsonProperty("probaAlegere")
	List<ProbaDTO> probaAlegere;
	@JsonProperty("limbaMaterna")
	List<ProbaDTO> limbaMaterna;
	@JsonProperty("limbaModerna")
	List<ProbaDTO> limbaModerna;
	
	public List<ProbaDTO> getProbaObligatorie() {
		return probaObligatorie;
	}
	public void setProbaObligatorie(List<ProbaDTO> probaObligatorie) {
		this.probaObligatorie = probaObligatorie;
	}
	public List<ProbaDTO> getProbaAlegere() {
		return probaAlegere;
	}
	public void setProbaAlegere(List<ProbaDTO> probaAlegere) {
		this.probaAlegere = probaAlegere;
	}
	public List<ProbaDTO> getLimbaMaterna() {
		return limbaMaterna;
	}
	public void setLimbaMaterna(List<ProbaDTO> limbaMaterna) {
		this.limbaMaterna = limbaMaterna;
	}
	public List<ProbaDTO> getLimbaModerna() {
		return limbaModerna;
	}
	public void setLimbaModerna(List<ProbaDTO> limbaModerna) {
		this.limbaModerna = limbaModerna;
	}
	
}
