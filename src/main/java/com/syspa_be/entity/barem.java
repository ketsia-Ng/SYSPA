package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_barem")
public class barem {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codebarem;
private Long codefonction;
private String libelle;
private Double montant=(double)0.0;
private Boolean etat=true;
private Date datecreation=new Date();
public Long getCodebarem() {
	return codebarem;
}
public void setCodebarem(Long codebarem) {
	this.codebarem = codebarem;
}
public Long getCodefonction() {
	return codefonction;
}
public void setCodefonction(Long codefonction) {
	this.codefonction = codefonction;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
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
public barem(Long codebarem, Long codefonction, String libelle, Double montant, Boolean etat, Date datecreation) {
	super();
	this.codebarem = codebarem;
	this.codefonction = codefonction;
	this.libelle = libelle;
	this.montant = montant;
	this.etat = etat;
	this.datecreation = datecreation;
}
public barem() {
	super();
	// TODO Auto-generated constructor stub
}


}
