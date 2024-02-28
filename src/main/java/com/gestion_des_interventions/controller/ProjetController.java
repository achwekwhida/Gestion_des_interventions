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

import com.gestion_des_interventions.model.Projet;
import com.gestion_des_interventions.repository.ProjetRepo;

@RestController
@RequestMapping("/projets")
public class ProjetController {

    @Autowired
    private ProjetRepo projetRepo;

    @GetMapping
    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }

    @GetMapping("/{id}")
    public Projet getProjetById(@PathVariable Long id) {
        return projetRepo.findById(id).orElse(null);
    }
    @GetMapping("/getByTitre/{titre}")
    public Projet getProjetByTitre(@PathVariable String titre) {
        return projetRepo.findByTitre(titre);
    }

    @PostMapping
    public Projet createProjet(@RequestBody Projet projet) {
        return projetRepo.save(projet);
    }

    @PutMapping("/{id}")
    public Projet updateProjet(@PathVariable Long id, @RequestBody Projet updatedProjet) {
        return projetRepo.findById(id)
                .map(projet -> {
                    projet.setTitre(updatedProjet.getTitre());
                    projet.setDateDeLancement(updatedProjet.getDateDeLancement());
                    return projetRepo.save(projet);
                })
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetRepo.deleteById(id);
    }
    
}