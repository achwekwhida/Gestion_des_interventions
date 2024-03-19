package com.gestion_des_interventions.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name=" Admin")
public class Admin extends Compte{
	
	@OneToOne (mappedBy = "admin")
	private RapportSuivi rapport_suivie;
	@OneToMany
	private List <Intervention> intervention = new ArrayList<Intervention>();
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
	private List<Client> client = new ArrayList<Client>();
	@OneToMany
	private List<EquipeTech> equipeTech = new ArrayList<EquipeTech>();
	@OneToMany
	private List<Produit> produit = new ArrayList<Produit>();
	@OneToMany
	private List<Reclamation> reclamation = new ArrayList<Reclamation>();
	
	public Admin () {
		super();
	}
	
	
	
}
