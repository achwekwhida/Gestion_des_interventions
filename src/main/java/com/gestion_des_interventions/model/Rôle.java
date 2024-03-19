package com.gestion_des_interventions.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Rôle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	private String type;
	/*private Boolean type  ---> 1 = admin /0=utilisateur*/
	
	@OneToMany
	 private List <Compte> comptes = new ArrayList<Compte>();
	
	
	
	
	public Rôle(Long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}


	public Rôle() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
