package com.gestion_des_interventions.model;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
@Entity
public class Produit {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private Date dateDeLancement;
    
    @ManyToOne
    private Admin admin ; 
    @ManyToMany
    Set<Reclamation> reclamation;

	public Produit() {
		super();
	}
	public Produit(Long id, String nom, Date dateDeLancement) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateDeLancement = dateDeLancement;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom ;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDateDeLancement() {
		return dateDeLancement;
	}
	public void setDateDeLancement(Date dateDeLancement) {
		this.dateDeLancement = dateDeLancement;
	}
    

}
