package com.gestion_des_interventions.repository;


 
 
 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Admin;




@Repository

public interface AdminRepo extends JpaRepository<Admin, Long> {
	
	 Optional<Admin> findByNom(String nom);
	 @Query("SELECT a FROM ClientRepo a WHERE a.nom = :nom")
	    Optional<Admin> findBynom(@Param("nom") String nom);
	 
	 
	 Optional<Admin> findByPrenom(String prénom);
	 @Query("SELECT a FROM ClientRepo a WHERE a.prénom = :prénom")
	 Optional<Admin> findByprenom(@Param("prénom") String prénom);
	 Optional<Admin> findByEmail(String email);
	 @Query("SELECT a FROM ClientRepo a WHERE a.email = :email")
	 Optional <Admin> findByemail(@Param("email") String email);
	 
}
