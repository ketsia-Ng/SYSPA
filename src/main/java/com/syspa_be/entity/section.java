package com.syspa_be.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_section")
public class section {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long codesection;
private String nomsection;
private Boolean etat=true;
@JsonFormat(pattern = "yyyy-MM-dd")
private Date datecreation=new Date();
public Long getCodesection() {
	return codesection;
}
public void setCodesection(Long codesection) {
	this.codesection = codesection;
}
public String getNomsection() {
	return nomsection;
}
public void setNomsection(String nomsection) {
	this.nomsection = nomsection;
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
public section(Long codesection, String nomsection, Boolean etat, Date datecreation) {
	super();
	this.codesection = codesection;
	this.nomsection = nomsection;
	this.etat = etat;
	this.datecreation = datecreation;
}

public section() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "section [codesection=" + codesection + ", nomsection=" + nomsection + ", etat=" + etat + ", datecreation="
			+ datecreation + "]";
}

}
