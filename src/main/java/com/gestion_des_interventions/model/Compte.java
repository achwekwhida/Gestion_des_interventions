package com.gestion_des_interventions.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Compte {
 @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 @Column( updatable = false, nullable = false)
protected Long id;
 @Column( updatable = false, nullable = false)
 protected String cin;
 protected String nom;
 protected String prénom;
 @Column(insertable=true, updatable=true, nullable=false)
 protected String email;
 protected String tel ;
 protected String sexe;
 protected Date dateDeNaissance;
 @Column(insertable=true, updatable=true, nullable=false)
 protected String mdp;
 protected Boolean active;
 protected String civilité;
 
 @ManyToOne
 private Rôle rôle;
 
 
 
 
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrénom() {
	return prénom;
}
public void setPrénom(String prénom) {
	this.prénom = prénom;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getSexe() {
	return sexe;
}
public void setSexe(String sexe) {
	this.sexe = sexe;
}
public Date getDateDeNaissance() {
	return dateDeNaissance;
}
public void setDateDeNaissance(Date dateDeNaissance) {
	this.dateDeNaissance = dateDeNaissance;
}




public String getCivilité() {
	return civilité;
}
public void setCivilité(String civilité) {
	this.civilité = civilité;
}
public String getCin() {
	return cin;
}
public void setCin(String cin) {
	this.cin = cin;
}
public Boolean getActive() {
	return active;
}
public void setActive(Boolean active) {
	this.active = active;
}
public String getMdp() {
	return mdp;
}
public void setMdp(String mdp) {
	this.mdp = mdp;
}


public Compte(Long id, String cin, String nom, String prénom, String email, String tel, String sexe,
		Date dateDeNaissance, String mdp, Boolean active , String civilité) {
	super();
	this.id = id;
	this.cin = cin;
	this.nom = nom;
	this.prénom = prénom;
	this.email = email;
	this.tel = tel;
	this.sexe = sexe;
	this.dateDeNaissance = dateDeNaissance;
	this.mdp = mdp;
	this.active = active;
	this.civilité = civilité;
}
public Compte() {
	super();
	// TODO Auto-generated constructor stub
	}



 
 

}
