package com.gestion_des_interventions.model;

import java.util.ArrayList;
import java.util.List;



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
	@OneToMany
	private List<Client> client = new ArrayList<Client>();
	@OneToMany
	private List<EquipeTech> equipeTech = new ArrayList<EquipeTech>();
	
}
