package com.gestion_des_interventions.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Intervention;
import jakarta.transaction.Transactional;



@Repository
public interface InterventionRepo extends JpaRepository<Intervention, Long>{
	@Transactional
	   Optional<Intervention> findByDateDebut(Date dateDebut);
		@Transactional
	   Optional<Intervention> findByDateFin(Date dateFin);
		@Transactional
		   Optional<Intervention> findByétat(String état);
		@Transactional
		  Optional<Intervention>  findBySolution(String solution);
		@Query(value = "  select état , count(*) from intervention group by état",nativeQuery = true)
		public List<Object> getCountGroupByEtat();
}
