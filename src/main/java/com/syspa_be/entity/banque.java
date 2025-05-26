package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_banque")
public class banque {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long codebanque;
private String nombanque;
private Boolean etat=true;
private Date datecreation=new Date();

public Long getCodebanque() {
	return codebanque;
}
public void setCodebanque(Long codebanque) {
	this.codebanque = codebanque;
}
public String getNombanque() {
	return nombanque;
}
public void setNombanque(String nombanque) {
	this.nombanque = nombanque;
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
public banque(Long codebanque, String nombanque, Boolean etat, Date datecreation) {
	super();
	this.codebanque = codebanque;
	this.nombanque = nombanque;
	this.etat = etat;
	this.datecreation = datecreation;
}
public banque() {
	super();
	// TODO Auto-generated constructor stub
}

}
