package com.gestion_des_interventions.controller;

import java.time.LocalDateTime;
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

import com.gestion_des_interventions.model.Admin;
import com.gestion_des_interventions.model.Client;
import com.gestion_des_interventions.model.Reclamation;
import com.gestion_des_interventions.repository.AdminRepo;
import com.gestion_des_interventions.repository.ClientRepo;
import com.gestion_des_interventions.repository.RecRepo;

@RestController
@RequestMapping("api/reclamation")
public class RecController {
	@Autowired
    private RecRepo recRepo ;
	
	@Autowired
    private ClientRepo clientRepo ;
	@Autowired
	private AdminRepo adminRepo;
	
	
	
	@GetMapping("/get_all")
	public List<Reclamation> getAll(){
		return this.recRepo.listDeRéclamationOrdoné();
	}
	
	//@Requestparam
	@GetMapping("/get_by_id/")
	public ResponseEntity<Reclamation> getReclamationByid(@RequestParam Long id) {
	    Optional<Reclamation>recexiste = this.recRepo.findById(id);
	    if (recexiste.isPresent()) {
	    	Reclamation intervention = recexiste.get();
	        return new ResponseEntity<>(intervention, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}
	
@GetMapping("/get_by_id/{id}")
	
public ResponseEntity<Reclamation> getIntervention(@PathVariable Long id) {
    Optional<Reclamation> recexiste = this.recRepo.findById(id);
    if (recexiste.isPresent()) {
    	Reclamation reclamation = recexiste.get();
        return new ResponseEntity<>(reclamation, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}}

@GetMapping("get_reclamation/{type}")
public  ResponseEntity<Reclamation> getByType(@PathVariable ("type") String type) {
	Optional<Reclamation> existeReclamation= this.recRepo.findByType(type);
			if ( existeReclamation.isPresent()) {
				 return new ResponseEntity<>(existeReclamation.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
}
@GetMapping("get_reclamation/{date}")
public  ResponseEntity<Reclamation> getByDate(@PathVariable ("descreption") LocalDateTime date) {
	Optional<Reclamation> existeReclamation= this.recRepo.findByDate(date);
			if ( existeReclamation.isPresent()) {
				 return new ResponseEntity<>(existeReclamation.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
@GetMapping("/get_by/{id}/date")
public ResponseEntity<String> getDateById(@PathVariable Long id) {
    Optional<Reclamation> existeReclamation = recRepo.findById(id);
    if (existeReclamation.isPresent()) {
        return new ResponseEntity<>("la date de lancement de cette reclamation est :" +existeReclamation.get().getDate(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Reclamation not found with that id "+ id,HttpStatus.NOT_FOUND);
    }
}	

@GetMapping("/get_by_id/{id}/etat")
public ResponseEntity <String> getEtatById(@PathVariable Long id ) {
    Optional<Reclamation> existeReclamation = recRepo.findById(id);
    if (existeReclamation.isPresent() ) {
    	 return new ResponseEntity<>("l'état de catte Reclamation est  :"+existeReclamation.get().getÉtat(), HttpStatus.OK);     
    	}
    else 
    	return new ResponseEntity<>("Reclamation not found with that id "+ id,HttpStatus.NOT_FOUND);
   }


@GetMapping("get_count_group_by_status")
	public List<Object[]>  getCountGroupByStatus(){ 
  return this .recRepo.getCountGroupByEtat();  }
	
	
@GetMapping("get_count_group_by_type")
public List<Object[]>  getCountGroupByType(){ 
return this .recRepo.getCountGroupByType();  }

	
	
	@DeleteMapping("/delet_reclamation_by_id/{id}")
	
	public ResponseEntity<String> supprimerReclamation(@PathVariable Long id) { 
	    if (this.recRepo.existsById(id)) {
	        this.recRepo.deleteById(id);
	        return new ResponseEntity<>("Reclamation with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Reclamation with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}


	@DeleteMapping("/delet_all_reclamations")
	public   ResponseEntity<String> supprimerReclamation() {
		this.recRepo.deleteAll();
		return  new ResponseEntity<>("all Reclamation were successfully deleted",HttpStatus.OK); 
		}

	
	
	public  Reclamation creatReclamation( Reclamation reclamation , Client client , Admin admin) {
		
		Reclamation nv= new Reclamation();
		nv.setDate(LocalDateTime.now());
		nv.setId(reclamation.getId());
		nv.setÉtat(reclamation.getÉtat());
		nv.setDescriptionDeProbléme(reclamation.getDescriptionDeProbléme());
		nv.setClient(client);
		nv.setAdmin(admin);
		 this.recRepo.save(nv);
		 return recRepo.save(nv);
	}
	public  Client  findClientById(Long clientid) throws Exception {
		Optional<Client> existeClient =clientRepo.findById(clientid);
		if (existeClient.isPresent()) {
			return existeClient.get();}
		else 
			throw new Exception("client not fond with id : "+clientid);
		}
	public Admin findAdminById (long adminid) throws Exception{
		Optional<Admin> existeAdmin =adminRepo.findById(adminid);
		if (existeAdmin .isPresent()) {
			return existeAdmin .get();}
		else 
			throw new Exception("admin not fond with id : "+adminid);
	}
		
	
	
	@PostMapping("/add_reclamation/client/{clientid}/admin/{adminid}")
	public ResponseEntity<String> ajoutReclamation (@RequestBody Reclamation reclamation,@PathVariable Long clientid,@PathVariable Long adminid ) throws Exception{
		Optional<Reclamation> recExiste =recRepo.findById(reclamation.getId());
		
		if (recExiste.isPresent()) {
			return new ResponseEntity<>("Reclamation with id  " + reclamation.getId() + " already exists.", HttpStatus.CONFLICT); 	 
			}
		
			else {
				 Client client = this.findClientById(clientid);
				 Admin admin = this.findAdminById(adminid);
						 this.creatReclamation(reclamation,client,admin);
				return new ResponseEntity<>("Reclamation with id  " + reclamation.getId() + " added successfully.", HttpStatus.CREATED);
			}
		
	}

	
	@PutMapping("/update_reclamation/{iid}")
	public ResponseEntity<String> updateReclamation (@PathVariable("iid") Long id , @RequestBody Reclamation reclamation) {
		
		Optional<Reclamation > existeReclamation = recRepo.findById(id);
		if (existeReclamation.isPresent()) {
			Reclamation nv=existeReclamation.get();
			nv.setDate(reclamation.getDate());		
			nv.setÉtat(reclamation.getÉtat());
			nv.setDescriptionDeProbléme(reclamation.getDescriptionDeProbléme());
			nv.setType(reclamation.getType());
        	 return new ResponseEntity<> ("Reclamation  with ID: "+ id + " updated successfully",HttpStatus.OK);
        	}
        else
        return new ResponseEntity<> ("Reclamation not found with ID: "+ id ,HttpStatus.CONFLICT);
        }
	

	}



	
 