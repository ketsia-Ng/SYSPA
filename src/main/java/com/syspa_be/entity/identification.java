package com.syspa_be.entity;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="tab_identification")
public class identification {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long identifiant;
private String nom;
private String postnom;
private String prenom;
private String matricule;
private String genre;
@JsonFormat(pattern = "yyyy-MM-dd")
private Date datenaiss=new Date();
@JsonFormat(pattern = "yyyy-MM-dd")
private Date dateengagement=new Date();
private String lieunaiss;
@JsonFormat(pattern = "yyyy-MM-dd")
private Date datecreation=new Date();
public Long getIdentifiant() {
	return identifiant;
}
public void setIdentifiant(Long identifiant) {
	this.identifiant = identifiant;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPostnom() {
	return postnom;
}
public void setPostnom(String postnom) {
	this.postnom = postnom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getMatricule() {
	return matricule;
}
public void setMatricule(String matricule) {
	this.matricule = matricule;
}
public String getGenre() {
	return genre;
}
public void setGenre(String genre) {
	this.genre = genre;
}
public Date getDatenaiss() {
	return datenaiss;
}
public void setDatenaiss(Date datenaiss) {
	this.datenaiss = datenaiss;
}
public Date getDateengagement() {
	return dateengagement;
}
public void setDateengagement(Date dateengagement) {
	this.dateengagement = dateengagement;
}
public String getLieunaiss() {
	return lieunaiss;
}
public void setLieunaiss(String lieunaiss) {
	this.lieunaiss = lieunaiss;
}
public Date getDatecreation() {
	return datecreation;
}
public void setDatecreation(Date datecreation) {
	this.datecreation = datecreation;
}
public identification(Long identifiant, String nom, String postnom, String prenom, String matricule, String genre,
		Date datenaiss, Date dateengagement, String lieunaiss, Date datecreation) {
	super();
	this.identifiant = identifiant;
	this.nom = nom;
	this.postnom = postnom;
	this.prenom = prenom;
	this.matricule = matricule;
	this.genre = genre;
	this.datenaiss = datenaiss;
	this.dateengagement = dateengagement;
	this.lieunaiss = lieunaiss;
	this.datecreation = datecreation;
}

public identification() {
	super();
	// TODO Auto-generated constructor stub
}

}
