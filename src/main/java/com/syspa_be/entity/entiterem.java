package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_entiterem")
public class entiterem {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codeentite;
private Long codeprovince;
private Long codesection;
private String nomentite;
private Boolean etat=true;
private Date datecreation=new Date();
public Long getCodeentite() {
	return codeentite;
}
public void setCodeentite(Long codeentite) {
	this.codeentite = codeentite;
}
public Long getCodeprovince() {
	return codeprovince;
}
public void setCodeprovince(Long codeprovince) {
	this.codeprovince = codeprovince;
}
public Long getCodesection() {
	return codesection;
}
public void setCodesection(Long codesection) {
	this.codesection = codesection;
}
public String getNomentite() {
	return nomentite;
}
public void setNomentite(String nomentite) {
	this.nomentite = nomentite;
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
public entiterem(Long codeentite, Long codeprovince, Long codesection, String nomentite, Boolean etat,
		Date datecreation) {
	super();
	this.codeentite = codeentite;
	this.codeprovince = codeprovince;
	this.codesection = codesection;
	this.nomentite = nomentite;
	this.etat = etat;
	this.datecreation = datecreation;
}

public entiterem() {
	super();
	// TODO Auto-generated constructor stub
}


}
