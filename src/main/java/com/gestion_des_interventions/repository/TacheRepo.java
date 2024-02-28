package com.gestion_des_interventions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion_des_interventions.model.Tache;

public interface TacheRepo extends JpaRepository<Tache, Long>{

}
