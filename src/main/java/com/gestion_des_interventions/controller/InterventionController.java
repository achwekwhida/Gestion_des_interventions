package com.gestion_des_interventions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_des_interventions.model.Intervention;
import com.gestion_des_interventions.repository.InterventionRepo;

@RestController
@RequestMapping("/interventions")
public class InterventionController{
	@Autowired
	    private InterventionRepo interventionRepo;

	    @GetMapping
	    public List<Intervention> getAllInterventions() {
	        return interventionRepo.findAll();
	    }

	    @GetMapping("/{id}")
	    public Intervention getInterventionById(@PathVariable Long id) {
	        return interventionRepo.findById(id).orElse(null);
	    }

	    @PostMapping
	    public Intervention createIntervention(@RequestBody Intervention intervention) {
	        return interventionRepo.save(intervention);
	    }
	    @PutMapping("/{id}")
	    public Intervention updateIntervention(@PathVariable Long id, @RequestBody Intervention updatedIntervention) {
	        return interventionRepo.findById(id)
	                .map(intervention -> {
	                    intervention.setDateDebut(updatedIntervention.getDateDebut());
	                    intervention.setDateFin(updatedIntervention.getDateFin());
	                    intervention.setÉtat(updatedIntervention.getÉtat());
	                    intervention.setDescriptionProbléme(updatedIntervention.getDescriptionProbléme());
	                    return interventionRepo.save(intervention);
	                })
	                .orElse(null);}

	    @DeleteMapping("/{id}")
	    public void deleteIntervention(@PathVariable Long id) {
	        interventionRepo.deleteById(id);
	    }
	}

	
	

