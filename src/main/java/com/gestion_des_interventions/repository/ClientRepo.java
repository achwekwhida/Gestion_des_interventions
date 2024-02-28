package com.gestion_des_interventions.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Client;



@Repository
public interface ClientRepo extends JpaRepository<Client, Long>{
	 Optional<Client> findByNom(String nom);

	 @Query("SELECT c FROM ClientRepo c WHERE c.nom = :prenom")
	    List<ClientRepo> findByPrenom(@Param("prenom") String prenom);
	 @Query("SELECT c FROM ClientRepo c WHERE c.DateDeNaissance = :DateDeNaissance")
	 Optional<ClientRepo> findByDateDeNaissance(@Param("DateDeNaissance") Date DateDeNaissance);

}