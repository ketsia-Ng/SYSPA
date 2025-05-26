package com.syspa_be.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class identificationModel {
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
	private Long id;
	private Long codesection;
	private String nomsection;
	private Long codeprovince;
	private String nomprovince;
	private Long codeentite;
	private String nomentite;
	private Long codeunite;
	private String nomunite;
	private Long codeposition;
	private String nomposition;
	private Long codefonction;
	private String nomfonction;
	private Long codebarem;
	private String libelle;
	private String intitule;
	private String sigle;
	private Double montant=(double)0.0;
	private Long codebanque;
	private String nombanque;
	private Integer validation;
	private Boolean etat;
	private Long effectif;
	
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
	public String getNomsection() {
		return nomsection;
	}
	public void setNomsection(String nomsection) {
		this.nomsection = nomsection;
	}
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
	public Long getCodeentite() {
		return codeentite;
	}
	public void setCodeentite(Long codeentite) {
		this.codeentite = codeentite;
	}
	public String getNomentite() {
		return nomentite;
	}
	public void setNomentite(String nomentite) {
		this.nomentite = nomentite;
	}
	public Long getCodeunite() {
		return codeunite;
	}
	public void setCodeunite(Long codeunite) {
		this.codeunite = codeunite;
	}
	public String getNomunite() {
		return nomunite;
	}
	public void setNomunite(String nomunite) {
		this.nomunite = nomunite;
	}
	
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
	public Long getCodefonction() {
		return codefonction;
	}
	
	public Long getCodebarem() {
		return codebarem;
	}
	public void setCodebarem(Long codebarem) {
		this.codebarem = codebarem;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public void setCodefonction(Long codefonction) {
		this.codefonction = codefonction;
	}
	public String getNomfonction() {
		return nomfonction;
	}
	public void setNomfonction(String nomfonction) {
		this.nomfonction = nomfonction;
	}
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
	public Integer getValidation() {
		return validation;
	}
	public void setValidation(Integer validation) {
		this.validation = validation;
	}
	
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	
	public Long getEffectif() {
		return effectif;
	}
	public void setEffectif(Long effectif) {
		this.effectif = effectif;
	}
	
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public identificationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	
	public identificationModel(Long identifiant, String nom, String postnom, String prenom, String matricule,
			String genre, Date datenaiss, Date dateengagement, String lieunaiss, Date datecreation, Long id,
			Long codesection, String nomsection, Long codeprovince, String nomprovince, Long codeentite,
			String nomentite, Long codeunite, String nomunite, Long codeposition, String nomposition, Long codefonction,
			String nomfonction, Long codebarem, String libelle, String intitule, String sigle, Double montant,
			Long codebanque, String nombanque, Integer validation, Boolean etat, Long effectif) {
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
		this.id = id;
		this.codesection = codesection;
		this.nomsection = nomsection;
		this.codeprovince = codeprovince;
		this.nomprovince = nomprovince;
		this.codeentite = codeentite;
		this.nomentite = nomentite;
		this.codeunite = codeunite;
		this.nomunite = nomunite;
		this.codeposition = codeposition;
		this.nomposition = nomposition;
		this.codefonction = codefonction;
		this.nomfonction = nomfonction;
		this.codebarem = codebarem;
		this.libelle = libelle;
		this.intitule = intitule;
		this.sigle = sigle;
		this.montant = montant;
		this.codebanque = codebanque;
		this.nombanque = nombanque;
		this.validation = validation;
		this.etat = etat;
		this.effectif = effectif;
	}
	@Override
	public String toString() {
		return "identificationModel [identifiant=" + identifiant + ", nom=" + nom + ", postnom=" + postnom + ", prenom="
				+ prenom + ", matricule=" + matricule + ", genre=" + genre + ", datenaiss=" + datenaiss
				+ ", dateengagement=" + dateengagement + ", lieunaiss=" + lieunaiss + ", datecreation=" + datecreation
				+ ", id=" + id + ", codesection=" + codesection + ", nomsection=" + nomsection + ", codeprovince="
				+ codeprovince + ", nomprovince=" + nomprovince + ", codeentite=" + codeentite + ", nomentite="
				+ nomentite + ", codeunite=" + codeunite + ", nomunite=" + nomunite + ", codeposition=" + codeposition
				+ ", nomposition=" + nomposition + ", codefonction=" + codefonction + ", nomfonction=" + nomfonction
				+ ", codebanque=" + codebanque + ", nombanque=" + nombanque + ", validation=" + validation + ", etat="
				+ etat + "]";
	}
	
}
