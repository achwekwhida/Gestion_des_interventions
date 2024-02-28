package com.gestion_des_interventions.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="Equipe_technique")
public class EquipeTech  extends Compte{
	@ManyToOne
	private Admin admin;
}