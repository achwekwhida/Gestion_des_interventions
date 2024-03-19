package com.gestion_des_interventions.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gestion_des_interventions.model.EquipeTech;

import jakarta.transaction.Transactional;



@Repository
public interface EquipeTechRepo  extends JpaRepository<EquipeTech, Long>{

	@Transactional
	Optional<EquipeTech> findByCin(String cin);
	@Transactional
	Optional<EquipeTech> findByNom(String nom);
	@Transactional
	Optional<EquipeTech> findByPrénom(String prénom); 
	@Transactional
	Optional<EquipeTech> findBySexe(String sexe);
	@Transactional
	Optional<EquipeTech> findByEmail(String email);
	@Transactional
	Optional<EquipeTech> findByTel(String tel);
	@Transactional
	Optional<EquipeTech> findByMdp(String mdp);
	@Transactional
	Optional<EquipeTech> findByActive(Boolean active);
	@Transactional
	Optional<EquipeTech> findByCivilité(String civilité);
	/*@Transactional
	Admin findByDateNaissance(Date datenaiddance);*/
}
