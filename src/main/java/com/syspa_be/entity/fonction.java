package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_fonction")
public class fonction {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codefonction;
private Long codesection;
private String nomfonction;
private Boolean etat=true;
private Date datecreation =new Date();
public Long getCodefonction() {
	return codefonction;
}
public void setCodefonction(Long codefonction) {
	this.codefonction = codefonction;
}
public Long getCodesection() {
	return codesection;
}
public void setCodesection(Long codesection) {
	this.codesection = codesection;
}
public String getNomfonction() {
	return nomfonction;
}
public void setNomfonction(String nomfonction) {
	this.nomfonction = nomfonction;
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
public fonction(Long codefonction, Long codesection, String nomfonction, Boolean etat, Date datecreation) {
	super();
	this.codefonction = codefonction;
	this.codesection = codesection;
	this.nomfonction = nomfonction;
	this.etat = etat;
	this.datecreation = datecreation;
}
public fonction() {
	super();
}




}
