package com.gestion_des_interventions.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gestion_des_interventions.model.Reclamation;
import jakarta.transaction.Transactional;


@Repository
public interface RecRepo extends JpaRepository<Reclamation, Long> {
	@Transactional
  Optional<Reclamation> findByType(String type);
	@Transactional
    Optional<Reclamation> findByDate(LocalDateTime date);
	@Query(value = " select * from réclamation order by date desc" ,nativeQuery = true)
	public List<Reclamation> listDeRéclamationOrdoné();
	
	@Query(value = "  select état , count(*) from réclamation group by état",nativeQuery = true)
	public List<Object[]> getCountGroupByEtat();
	
	@Query(value = "  select type , count(*) from réclamation group by type",nativeQuery = true)
	public List<Object[]> getCountGroupByType();
}
