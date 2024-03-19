package com.gestion_des_interventions.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion_des_interventions.model.Produit;

import jakarta.transaction.Transactional;

public interface ProduitRepo extends JpaRepository<Produit, Long>{
	
	
	@Transactional
	Optional<Produit> findByNom(String Nom);
	@Transactional
	Optional<Produit> findByDateDeLancement(Date dateDeLancement);
	
	@Query(value = " select * from produit order by date_de_lancement desc" ,nativeQuery = true)
	public List<Produit> listDeProduitOrdoné();
}
