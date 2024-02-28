package com.gestion_des_interventions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_des_interventions.model.Projet;

import jakarta.transaction.Transactional;

public interface ProjetRepo extends JpaRepository<Projet, Long>{
	
	
	@Transactional
	      Projet findByTitre(String titre);
	 
}
