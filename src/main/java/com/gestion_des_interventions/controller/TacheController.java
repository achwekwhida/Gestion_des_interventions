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

import com.gestion_des_interventions.model.Tache;
import com.gestion_des_interventions.repository.TacheRepo;

@RestController
@RequestMapping("/taches")
public class TacheController {

    @Autowired
    private TacheRepo tacheRepo;

    @GetMapping
    public List<Tache> getAllTaches() {
        return tacheRepo.findAll();
    }

    @GetMapping("/{id}")
    public Tache getTacheById(@PathVariable Long id) {
        return tacheRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Tache createTache(@RequestBody Tache tache) {
        return tacheRepo.save(tache);
    }

    @PutMapping("/{id}")
    public Tache updateTache(@PathVariable Long id, @RequestBody Tache updatedTache) {
        Tache existingTache = tacheRepo.findById(id).orElse(null);
        if (existingTache != null) {
            existingTache.setDateDebut(updatedTache.getDateDebut());
            existingTache.setDateFin(updatedTache.getDateFin());
            return tacheRepo.save(existingTache);
        } else {
            return null; 
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTache(@PathVariable Long id) {
        tacheRepo.deleteById(id);
    }
}