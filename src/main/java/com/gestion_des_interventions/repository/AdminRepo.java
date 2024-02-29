package com.gestion_des_interventions.repository;


 
 
 




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.Admin;





@Repository
@EnableJpaRepositories 
public interface AdminRepo extends JpaRepository<Admin, Long> {
	 //Optional<Admin> findByNomAdmin(String nom);
	 
	
}
