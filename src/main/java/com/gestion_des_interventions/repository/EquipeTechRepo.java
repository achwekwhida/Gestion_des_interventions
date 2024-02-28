package com.gestion_des_interventions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion_des_interventions.model.EquipeTech;



@Repository
public interface EquipeTechRepo  extends JpaRepository<EquipeTech, Long>{

}
