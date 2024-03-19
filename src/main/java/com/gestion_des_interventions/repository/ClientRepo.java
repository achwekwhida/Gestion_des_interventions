package com.gestion_des_interventions.repository;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.gestion_des_interventions.model.Client;

import jakarta.transaction.Transactional;



@Repository
public interface ClientRepo extends JpaRepository<Client, Long>{
	 //Optional<Client> findByNomClient(String nom);
	@Transactional
	Optional<Client> findByCin(String cin);
	@Transactional
	List<Client> findByNom(String nom);
	@Transactional
	Optional<Client> findByPrénom(String prénom); 
	@Transactional
	Optional<Client> findBySexe(String sexe);
	@Transactional
	Optional<Client > findByEmail(String email);
	@Transactional
	Optional<Client> findByTel(String tel);
	@Transactional
	Optional<Client> findByMdp(String mdp);
	@Transactional
	Optional<Client> findByActive(Boolean active);
	@Transactional
	Optional<Client> findByCivilité(String civilité);
	@Transactional
	  
	/*@Transactional
	Optional<Client>findByDateNaissance(LocalDateTime dateDeNaissance);*/
	
	@Query(value="SELECT nom_client, COUNT(*) AS nombre_de_clients FROM clients GROUP BY nom_client HAVING COUNT(*) > 1",nativeQuery = true )
	public Client findBynom();


}