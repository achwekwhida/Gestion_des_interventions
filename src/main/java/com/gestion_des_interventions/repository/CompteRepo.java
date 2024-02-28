package com.gestion_des_interventions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Compte;


@Repository
public interface CompteRepo extends JpaRepository<Compte, Long> {

}
