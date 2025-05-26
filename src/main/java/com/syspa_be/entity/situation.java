package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_situation")
public class situation {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
private Long identifiant;
private Long codesection;
private Long codeprovince;
private Long codeentite;
private Long codeunite;
private Long codeposition;
private Long codefonction;
private Long codebanque;
private Long codebarem;
private Integer validation=0;
private Date datecreation=new Date();

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Long getCodesection() {
	return codesection;
}
public void setCodesection(Long codesection) {
	this.codesection = codesection;
}
public Long getCodeprovince() {
	return codeprovince;
}
public void setCodeprovince(Long codeprovince) {
	this.codeprovince = codeprovince;
}
public Long getCodeentite() {
	return codeentite;
}
public void setCodeentite(Long codeentite) {
	this.codeentite = codeentite;
}
public Long getCodeunite() {
	return codeunite;
}
public void setCodeunite(Long codeunite) {
	this.codeunite = codeunite;
}

public Long getCodeposition() {
	return codeposition;
}
public void setCodeposition(Long codeposition) {
	this.codeposition = codeposition;
}
public Long getCodefonction() {
	return codefonction;
}
public void setCodefonction(Long codefonction) {
	this.codefonction = codefonction;
}
public Long getCodebanque() {
	return codebanque;
}
public void setCodebanque(Long codebanque) {
	this.codebanque = codebanque;
}

public Long getCodebarem() {
	return codebarem;
}
public void setCodebarem(Long codebarem) {
	this.codebarem = codebarem;
}
public Integer getValidation() {
	return validation;
}
public void setValidation(Integer validation) {
	this.validation = validation;
}
public Date getDatecreation() {
	return datecreation;
}
public void setDatecreation(Date datecreation) {
	this.datecreation = datecreation;
}
public Long getIdentifiant() {
	return identifiant;
}

public void setIdentifiant(Long identifiant) {
	this.identifiant = identifiant;
}
public situation() {
	super();
	// TODO Auto-generated constructor stub
}


}
