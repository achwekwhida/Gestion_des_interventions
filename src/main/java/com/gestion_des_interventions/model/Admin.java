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
	

	public Admin(Long id, String nom, String prénom, String email, String tel, String sexe, Date dateDeNaissance,
			String mdp) {
		super(id, nom, prénom, email, tel, sexe, dateDeNaissance, mdp);
		// TODO Auto-generated constructor stub
	}
	public Admin () {
		super();
	}
	
	
	
}
