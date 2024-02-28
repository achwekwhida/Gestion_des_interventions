package com.gestion_des_interventions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_des_interventions.model.RapportSuivi;
import com.gestion_des_interventions.repository.RapportSuiviRepo;

@RestController
@RequestMapping("/rapports-suivi")

public class RapportSuiviController {
	
	    @Autowired
	    private RapportSuiviRepo rapportSuiviRepo;

	    @GetMapping
	    public List<RapportSuivi> getAllRapportsSuivi() {
	        return rapportSuiviRepo.findAll();
	    }

	    @GetMapping("/{id}")
	    public RapportSuivi getRapportSuiviById(@PathVariable Long id) {
	        return rapportSuiviRepo.findById(id).orElse(null);
	    }
	}

