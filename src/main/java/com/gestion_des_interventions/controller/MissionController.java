package com.gestion_des_interventions.controller;

import java.util.Date;
import java.util.List;
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
import com.gestion_des_interventions.model.Mission;
import com.gestion_des_interventions.repository.MissionRepo;


@RestController
@RequestMapping("api/missions")
public class MissionController {

    @Autowired
   private MissionRepo missionRepo;
    
    
    
    @GetMapping("/get_all")
	public List<Mission> getAll(){
		return this.missionRepo.findAll();
	}
	
	//@Requestparam
	@GetMapping("/get_by_id/")
	public ResponseEntity<Mission> getMissionByid(@RequestParam Long id) {
	    Optional<Mission> missionexiste = this.missionRepo.findById(id);
	    if (missionexiste.isPresent()) {
	    	Mission mission = missionexiste.get();
	        return new ResponseEntity<>(mission, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}
	
@GetMapping("/get_by_id/{id}")
	
public ResponseEntity<Mission> getMissByid(@PathVariable Long id) {
    Optional<Mission> missionexiste = this.missionRepo.findById(id);
    if (missionexiste.isPresent()) {
    	Mission mission = missionexiste.get();
        return new ResponseEntity<>(mission, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}}


@GetMapping("get_mission/{date_debut}")
public  ResponseEntity<Mission> getByDateDebut(@PathVariable ("date_debut") Date dateDebut) {
	Optional<Mission> existeMission= this.missionRepo.findByDateDebut(dateDebut);
			if ( existeMission.isPresent()) {
				 return new ResponseEntity<>(existeMission.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}

@GetMapping("get_mission/{date_fin}")
public  ResponseEntity<Mission> getByDateFin(@PathVariable ("date_debut") Date dateFin) {
	Optional<Mission> existeMission= this.missionRepo.findByDateFin(dateFin);
			if ( existeMission.isPresent()) {
				 return new ResponseEntity<>(existeMission.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
	//@@PathVariabl
	@DeleteMapping("/delet_mission_by_id/{id}")
	
	public ResponseEntity<String> supprimerMission(@PathVariable Long id) { 
	    if (this.missionRepo.existsById(id)) {
	        this.missionRepo.deleteById(id);
	        return new ResponseEntity<>("Mission with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Mission with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}

	//delete by id @paramMissionintervention_by_id/")
	public ResponseEntity<String> suppMission(@RequestParam Long id) { 
	    if (this.missionRepo.existsById(id)) {
	        this.missionRepo.deleteById(id);
	        return new ResponseEntity<>("Mission with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("InterveMissionntion with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}
	

	
	// delete all 
	@DeleteMapping("/delet_all_missions")
	public   ResponseEntity<String> supprimerMission() {
		this.missionRepo.deleteAll();
		return  new ResponseEntity<>("all Mission were successfully deleted",HttpStatus.OK); 
		}

	
	
	@PostMapping("/add_mission")
	public ResponseEntity<String> ajouterMission (@RequestBody Mission mission ) {
		
		
		if (this.missionRepo.existsById(mission.getId())) {
			return new ResponseEntity<>("Mission with id  " + mission.getId() + " already exists.", HttpStatus.CONFLICT); 	}
		else {
			Mission nv= new Mission ();
		nv.setDateDebut(mission.getDateDebut());		
		nv.setDateFin(mission.getDateFin());
		nv.setId(mission.getId());
		
		this.missionRepo.save(nv);
		
		return new ResponseEntity<>("Mission with id  " + mission.getId() + " added successfully.", HttpStatus.CREATED);
		}
		}
	
	@PostMapping("/add_mission/{id}")
	public ResponseEntity<String> ajoutMission (@PathVariable Long id , @RequestParam Date datedebut, @RequestParam Date datefin){
		Optional<Mission> adexiste =missionRepo.findById(id);
		if (adexiste.isPresent()) {
			return null ;}
			else {
				Mission mission= new Mission( );
				 this.missionRepo.save(mission);
				return new ResponseEntity<>("Mission with id  " + mission.getId() + " added successfully.", HttpStatus.CREATED);
			}
		
	}

	
	@PutMapping("/update_mission/{iid}")
	public ResponseEntity<String> updateMission (@PathVariable("iid") Long id , @RequestBody Mission mission) {
		
		Optional<Mission > existeMission = missionRepo.findById(id);
		if (existeMission.isPresent()) {
			Mission nv=existeMission.get();
			nv.setDateDebut(mission.getDateDebut());		
			nv.setDateFin(mission.getDateFin());
			
			
        	 return new ResponseEntity<> ("Mission  with ID: "+ id + " updated successfully",HttpStatus.OK);
        	}
        else
        return new ResponseEntity<> ("Mission not found with ID: "+ id ,HttpStatus.CONFLICT);
        }
	
	
	
	
	// Méthode pour afficher le nom d'un compte
	@GetMapping("/get_by/{id}/date_debut")
	public ResponseEntity<String> getDateDebutyId(@PathVariable Long id) {
	    Optional<Mission> existeMission = missionRepo.findById(id);
	    if (existeMission.isPresent()) {
	        return new ResponseEntity<>("la date de debut de cette Mission est :" +existeMission.get().getDateDebut(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@GetMapping("/get_by_id/{id}/Date_fin")
	public ResponseEntity<String> finddateFinyId(@PathVariable Long id) {
        Optional<Mission> existeMission = missionRepo.findById(id);
        if (existeMission.isPresent() ) {
        	 return new ResponseEntity<>("la date de fin de cette intervention est  :"+existeMission.get().getDateFin(), HttpStatus.OK);     
        	}
        else {
        	return new ResponseEntity<>("intervention not found with that id "+ id,HttpStatus.NOT_FOUND);
        }
	}}
	
	

	
	