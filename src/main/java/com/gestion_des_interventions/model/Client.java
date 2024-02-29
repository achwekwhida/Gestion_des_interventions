package com.gestion_des_interventions.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Client")
public class Client extends Compte {
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;
	@OneToMany
	private List<Intervention>interventions= new ArrayList<Intervention>();


}
