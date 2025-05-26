package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity()
@Table(name="tab_detailbarem")
public class detailbarem {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private Long codebarem;
private String intitule;
private String sigle;
private Double montant;
private Boolean etat=true;
private Date datecreation=new Date();
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCodebarem() {
	return codebarem;
}
public void setCodebarem(Long codebarem) {
	this.codebarem = codebarem;
}
public String getIntitule() {
	return intitule;
}
public void setIntitule(String intitule) {
	this.intitule = intitule;
}
public String getSigle() {
	return sigle;
}
public void setSigle(String sigle) {
	this.sigle = sigle;
}
public Double getMontant() {
	return montant;
}
public void setMontant(Double montant) {
	this.montant = montant;
}
public Boolean getEtat() {
	return etat;
}
public void setEtat(Boolean etat) {
	this.etat = etat;
}
public Date getDatecreation() {
	return datecreation;
}
public void setDatecreation(Date datecreation) {
	this.datecreation = datecreation;
}

public detailbarem(Long id, Long codebarem, String intitule, String sigle, Double montant, Boolean etat,
		Date datecreation) {
	super();
	this.id = id;
	this.codebarem = codebarem;
	this.intitule = intitule;
	this.sigle = sigle;
	this.montant = montant;
	this.etat = etat;
	this.datecreation = datecreation;
}

public detailbarem() {
	super();
	// TODO Auto-generated constructor stub
}

}
