package com.syspa_be.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="tab_position")
public class position {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codeposition;
	private String nomposition;
	private Boolean etat=true;
	private Date datecreation=new Date();
	
	public Long getCodeposition() {
		return codeposition;
	}
	public void setCodeposition(Long codeposition) {
		this.codeposition = codeposition;
	}
	public String getNomposition() {
		return nomposition;
	}
	public void setNomposition(String nomposition) {
		this.nomposition = nomposition;
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
	public position(Long codeposition, String nomposition, Boolean etat, Date datecreation) {
		super();
		this.codeposition = codeposition;
		this.nomposition = nomposition;
		this.etat = etat;
		this.datecreation = datecreation;
	}
	public position() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
