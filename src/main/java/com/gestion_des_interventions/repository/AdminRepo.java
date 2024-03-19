package com.gestion_des_interventions.repository;




import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Admin;

import jakarta.transaction.Transactional;





@Repository
@EnableJpaRepositories 
public interface AdminRepo extends JpaRepository<Admin, Long> {
	@Transactional
	Optional<Admin> findByCin(String cin);
	@Transactional
	Optional<Admin> findByNom(String nom);
	@Transactional
	Optional<Admin> findByPrénom(String prénom); 
	@Transactional
	Optional<Admin> findBySexe(String sexe);
	@Transactional
	Optional<Admin> findByEmail(String email);
	@Transactional
	Optional<Admin> findByTel(String tel);
	@Transactional
	Optional<Admin> findByMdp(String mdp);
	@Transactional
	Optional<Admin> findByActive(Boolean active);
	@Transactional
	Optional<Admin> findByCivilité(String civilité);
	/*@Transactional
	Optional<Admin> findByDateNaissance(Date dateDeNaissance);
	 */
	
}
