package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_province")
public class province {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codeprovince;
private String nomprovince;
private Boolean etat=true;
private Date datecreation=new Date();
public Long getCodeprovince() {
	return codeprovince;
}
public void setCodeprovince(Long codeprovince) {
	this.codeprovince = codeprovince;
}
public String getNomprovince() {
	return nomprovince;
}
public void setNomprovince(String nomprovince) {
	this.nomprovince = nomprovince;
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
public province(Long codeprovince, String nomprovince, Boolean etat, Date datecreation) {
	super();
	this.codeprovince = codeprovince;
	this.nomprovince = nomprovince;
	this.etat = etat;
	this.datecreation = datecreation;
}
public province() {
	super();
	// TODO Auto-generated constructor stub
}



}
