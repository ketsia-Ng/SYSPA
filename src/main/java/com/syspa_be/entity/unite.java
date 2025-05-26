package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_unite")
public class unite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeunite;
	private Long codesection;
	private String nomunite;
	private Boolean etat=true;
	private Date datecreation=new Date();
	
	public Long getCodeunite() {
		return codeunite;
	}
	public void setCodeunite(Long codeunite) {
		this.codeunite = codeunite;
	}
	public Long getCodesection() {
		return codesection;
	}
	public void setCodesection(Long codesection) {
		this.codesection = codesection;
	}
	public String getNomunite() {
		return nomunite;
	}
	public void setNomunite(String nomunite) {
		this.nomunite = nomunite;
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
	public unite() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
