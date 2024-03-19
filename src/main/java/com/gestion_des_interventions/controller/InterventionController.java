package com.gestion_des_interventions.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gestion_des_interventions.model.Intervention;
import com.gestion_des_interventions.repository.InterventionRepo;

@RestController
@RequestMapping("api/interventions")
public class InterventionController{
	
	@Autowired
	    private InterventionRepo interventionRepo;
	

	@GetMapping("/get_all")
	public List<Intervention> getAll(){
		return this.interventionRepo.findAll();
	}
	
	//@Requestparam
	@GetMapping("/get_by_id/")
	public ResponseEntity<Intervention> getInterventionByid(@RequestParam Long id) {
	    Optional<Intervention> interexiste = this.interventionRepo.findById(id);
	    if (interexiste.isPresent()) {
	        Intervention intervention = interexiste.get();
	        return new ResponseEntity<>(intervention, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}
	
@GetMapping("/get_by_id/{id}")
	
public ResponseEntity<Intervention> getIntervention(@PathVariable Long id) {
    Optional<Intervention> interexiste = this.interventionRepo.findById(id);
    if (interexiste.isPresent()) {
    	Intervention intervention = interexiste.get();
        return new ResponseEntity<>(intervention, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}}

@GetMapping("get_intervention/{état}")
public  ResponseEntity<Intervention> getByEtat(@PathVariable ("état") String état) {
	Optional<Intervention> existeIntervention= this.interventionRepo.findByétat(état);
			if ( existeIntervention.isPresent()) {
				 return new ResponseEntity<>(existeIntervention.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
}
@GetMapping("get_intervention/{description}")
public  ResponseEntity<Intervention> getByDescription(@PathVariable ("descreption") String solution) {
	Optional<Intervention> existeIntervention= this.interventionRepo.findBySolution(solution);
			if ( existeIntervention.isPresent()) {
				 return new ResponseEntity<>(existeIntervention.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}

@GetMapping("get_intervention/{date_debut}")
public  ResponseEntity<Intervention> getByDateDebut(@PathVariable ("date_debut") Date dateDebut) {
	Optional<Intervention> existeIntervention= this.interventionRepo.findByDateDebut(dateDebut);
			if ( existeIntervention.isPresent()) {
				 return new ResponseEntity<>(existeIntervention.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}

@GetMapping("get_intervention/{date_fin}")
public  ResponseEntity<Intervention> getByDateFin(@PathVariable ("date_debut") Date dateFin) {
	Optional<Intervention> existeIntervention= this.interventionRepo.findByDateFin(dateFin);
			if ( existeIntervention.isPresent()) {
				 return new ResponseEntity<>(existeIntervention.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
	//@@PathVariabl
	@DeleteMapping("/delet_intervention_by_id/{id}")
	
	public ResponseEntity<String> supprimerIntervention(@PathVariable Long id) { 
	    if (this.interventionRepo.existsById(id)) {
	        this.interventionRepo.deleteById(id);
	        return new ResponseEntity<>("Intervention with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Intervention with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}

	//delete by id @param
	@DeleteMapping("/delet_intervention_by_id/")
	public ResponseEntity<String> suppIntervention(@RequestParam Long id) { 
	    if (this.interventionRepo.existsById(id)) {
	        this.interventionRepo.deleteById(id);
	        return new ResponseEntity<>("Intervention with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Intervention with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}
	

	
	// delete all 
	@DeleteMapping("/delet_all_interventions")
	public   ResponseEntity<String> supprimerIntervention() {
		this.interventionRepo.deleteAll();
		return  new ResponseEntity<>("all Intervention were successfully deleted",HttpStatus.OK); 
		}

	
	
	@PostMapping("/add_intervention")
	public ResponseEntity<String> ajouterIntervention (@RequestBody Intervention intervention ) {
		
		
		if (this.interventionRepo.existsById(intervention.getId())) {
			return new ResponseEntity<>("intervention with id  " + intervention.getId() + " already exists.", HttpStatus.CONFLICT); 	}
		else {
			Intervention nv= new Intervention ();
		nv.setDateDebut(intervention.getDateDebut());		
		nv.setDateFin(intervention.getDateFin());
		nv.setId(intervention.getId());
		nv.setÉtat(intervention.getÉtat());
		nv.setSolution(intervention.getSolution());
		this.interventionRepo.save(nv);
		
		return new ResponseEntity<>("intervention with id  " + intervention.getId() + " added successfully.", HttpStatus.CREATED);
		}
		}
	
	@PostMapping("/add_intervention/{id}")
	public ResponseEntity<String> ajoutIntervention (@PathVariable Long id , @RequestParam Date datedebut, @RequestParam Date datefin, @RequestParam String etat,
			@RequestParam String descreption){
		Optional<Intervention> adexiste =interventionRepo.findById(id);
		if (adexiste.isPresent()) {
			return null ;}
			else {
				Intervention intervention= new Intervention( );
				 this.interventionRepo.save(intervention);
				return new ResponseEntity<>("intervention with id  " + intervention.getId() + " added successfully.", HttpStatus.CREATED);
			}
		
	}

	
	@PutMapping("/update_intervention/{iid}")
	public ResponseEntity<String> updateIntervention (@PathVariable("iid") Long id , @RequestBody Intervention intervention) {
		
		Optional<Intervention > existeIntervention = interventionRepo.findById(id);
		if (existeIntervention.isPresent()) {
			Intervention nv=existeIntervention.get();
			nv.setDateDebut(intervention.getDateDebut());		
			nv.setDateFin(intervention.getDateFin());
			nv.setÉtat(intervention.getÉtat());
			nv.setSolution(intervention.getSolution());
        	 return new ResponseEntity<> ("intervention  with ID: "+ id + " updated successfully",HttpStatus.OK);
        	}
        else
        return new ResponseEntity<> ("Intervention not found with ID: "+ id ,HttpStatus.CONFLICT);
        }
	
	
	
	
	// Méthode pour afficher le nom d'un compte
	@GetMapping("/get_by/{id}/date_debut")
	public ResponseEntity<String> getDateDebutyId(@PathVariable Long id) {
	    Optional<Intervention> existeintervention = interventionRepo.findById(id);
	    if (existeintervention.isPresent()) {
	        return new ResponseEntity<>("la date de debut de cette intervention est :" +existeintervention.get().getDateDebut(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@GetMapping("/get_by_id/{id}/Date_fin")
	public ResponseEntity<String> finddateFinyId(@PathVariable Long id) {
        Optional<Intervention> existeintervention = interventionRepo.findById(id);
        if (existeintervention.isPresent() ) {
        	 return new ResponseEntity<>("la date de fin de cette intervention est  :"+existeintervention.get().getDateFin(), HttpStatus.OK);     
        	}
        else {
        	return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);
        }
        }
	
	
	@GetMapping("/get_by_id/{id}/etat")
	public ResponseEntity <String> getEtatById(@PathVariable Long id ) {
        Optional<Intervention> existeintervention = interventionRepo.findById(id);
        if (existeintervention.isPresent() ) {
        	 return new ResponseEntity<>("l'état de catte intervention est  :"+existeintervention.get().getÉtat(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);
       }
	

	@GetMapping("/get_by_id/{id}/solution")
	public ResponseEntity <String> getdescriptionById(@PathVariable Long id ) {
        Optional<Intervention> existeintervention = interventionRepo.findById(id);
        if (existeintervention.isPresent() ) {
        	 return new ResponseEntity<>("la descreption de solution du probléme de cette intervention est : "+existeintervention.get().getSolution(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);  }
	

	@GetMapping("get_count_group_by_type")
	public List<Object>  getCountGroupByStatus(){ 
			return this .interventionRepo.getCountGroupByEtat();  }
	

	
	
	
	}
	
	
	
	
	

