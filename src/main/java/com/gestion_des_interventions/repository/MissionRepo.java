package com.gestion_des_interventions.repository;

import java.util.Date;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.gestion_des_interventions.model.Mission;


import jakarta.transaction.Transactional;



@Repository
public interface MissionRepo extends JpaRepository<Mission, Long>{
	  Optional<Mission> findByDateDebut(Date dateDebut);
			@Transactional
		   Optional<Mission> findByDateFin(Date dateFin);
			
}
