package com.gestion_des_interventions.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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
import com.gestion_des_interventions.model.EquipeTech;
import com.gestion_des_interventions.repository.AdminRepo;
import com.gestion_des_interventions.repository.ClientRepo;
import com.gestion_des_interventions.repository.EquipeTechRepo;


@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	
	@Autowired
	private  AdminRepo adminRepo;

	@Autowired
    private  ClientRepo clientrepo;
	@Autowired
	private EquipeTechRepo equipeTechRepo;
	
	
	@GetMapping("/all")
	public List<Admin> getAll(){
		return this.adminRepo.findAll();
	}
	
	//@Requestparam
	@GetMapping("/get_by_id/")
	public ResponseEntity<Admin> getdByid(@RequestParam Long id) {
	    Optional<Admin> adexiste = this.adminRepo.findById(id);
	    if (adexiste.isPresent()) {
	        Admin admin = adexiste.get();
	        return new ResponseEntity<>(admin, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}
	//@@PathVariabl
	@GetMapping("/get_by_id/{id}")
	
	public ResponseEntity<String> supprimerAdmin(@PathVariable Long id) { 
	    if (this.adminRepo.existsById(id)) {
	        this.adminRepo.deleteById(id);
	        return new ResponseEntity<>("Admin with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Admin with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}

	//delete by id @param
	@DeleteMapping("/delet_admin_by_id/")
	public ResponseEntity<String> suppAdmin(@RequestParam Long id) { 
	    if (this.adminRepo.existsById(id)) {
	        this.adminRepo.deleteById(id);
	        return new ResponseEntity<>("Admin with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Admin with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}

	
	// delete all 
	@DeleteMapping("/delet_all_admin")
	public   ResponseEntity<Void> supprimerAdmin() {
		this.adminRepo.deleteAll();
		return ResponseEntity.noContent().build(); 
		}

	
	
	@PostMapping("/add_admin")
	public ResponseEntity<String> ajouterAdmin (@RequestBody Admin admin ) {
		
		
		if (this.adminRepo.existsById(admin.getId())) {
			
		return new ResponseEntity<>("admin with id  " + admin.getId() + " already exists.", HttpStatus.CONFLICT); 	}
		else {
		Admin nv= new Admin ();
		nv.setDateDeNaissance(admin.getDateDeNaissance());		
		nv.setEmail(admin.getEmail());
		nv.setId(admin.getId());
		nv.setNom(admin.getNom());
		nv.setPrénom(admin.getPrénom());
		nv.setSexe(admin.getSexe());
		nv.setTel(admin.getTel());
		nv.setMdp(admin.getMdp());
		
		this.adminRepo.save(nv);
		return new ResponseEntity<>("client with id  " + admin.getId() + " added successfully.", HttpStatus.CREATED);
		}
		}
	
	@PostMapping("/add_admin/{id}")
	public ResponseEntity<String> ajoutAdmin (@PathVariable Long id , @RequestParam String nom, @RequestParam String prénom, @RequestParam String email,
			@RequestParam String tel,@RequestParam String sexe,@RequestParam Date dateDeNaissance,@RequestParam String mdp){
		Optional<Admin> adexiste =adminRepo.findById(id);
		if (adexiste.isPresent()) {
			return null ;}
			else {
				Admin admin= new Admin( );
				 this.adminRepo.save(admin);
				return new ResponseEntity<>("client with id  " + admin.getId() + " added successfully.", HttpStatus.CREATED);
			}
		
	}

	
	@PutMapping("/update_admin/{iid}")
	public Admin updateAdmin (@PathVariable("iid") Long id , @RequestBody Admin admin) {
		
		Optional<Admin > existeAdmin = adminRepo.findById(id);
        if (existeAdmin.isPresent()) {
        	Admin nv=existeAdmin.get();
        	nv.setDateDeNaissance(admin.getDateDeNaissance());
        	nv.setEmail(admin.getEmail());
        	nv.setMdp(admin.getMdp());
        	nv.setNom(admin.getNom());
        	nv.setPrénom(admin.getPrénom());
        	nv.setSexe(admin.getSexe());
        	nv.setTel(admin.getTel());
        	return this.adminRepo.save(nv);
        	}
        else throw new NoSuchElementException("admin not found with ID: " + admin.getId());
         
	}
	
	
	
	// Méthode pour afficher le nom d'un compte
	@GetMapping("/get_by/{id}/name")
	public ResponseEntity<String> getNomById(@PathVariable Long id) {
	    Optional<Admin> existeadmin = adminRepo.findById(id);
	    if (existeadmin.isPresent()) {
	        return new ResponseEntity<>("the name of admin is :" +existeadmin.get().getNom(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("admin not found with that id "+ id,HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@GetMapping("/get_by_id/{id}/lastname")
	public ResponseEntity<String> findPrénomById(@PathVariable Long id) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	 return new ResponseEntity<>("the last name os admin is :"+existeadmin.get().getPrénom(), HttpStatus.OK);     
        	}
        else {
        	return new ResponseEntity<>("admin not found with that id "+ id,HttpStatus.NOT_FOUND);
        }
        }
	
	
	@GetMapping("/get_by_id/{id}/email")
	public ResponseEntity <String> getEmailById(@PathVariable Long id ) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	 return new ResponseEntity<>("the e-mail of admin is :"+existeadmin.get().getEmail(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>("admin not found with that id "+ id,HttpStatus.NOT_FOUND);
       }
	

	@GetMapping("/get_by_id/{id}/phone_number")
	public ResponseEntity <String> getTelById(@PathVariable Long id ) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	 return new ResponseEntity<>("the phone number os admin is: "+existeadmin.get().getTel(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>("admin not found with that id "+ id,HttpStatus.NOT_FOUND);  }
	
	
	
	
	@GetMapping("/get_by_id/{id}/sexe")
	public ResponseEntity <String> getSexeById(@PathVariable Long id ) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	 return new ResponseEntity<>("the sexe of admin is "+existeadmin.get().getSexe(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>("admin not found with that id "+ id,HttpStatus.NOT_FOUND);  }
	
	
	
	
	@GetMapping("/get_by_id/{id}/Date_of_birth")
	public ResponseEntity <Date> getDateById(@PathVariable Long id ) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	 return new ResponseEntity<>(existeadmin.get().getDateDeNaissance(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }
	
	
	
	/*@GetMapping("get_admin/{nom}")
	public  ResponseEntity<Admin> getByNom(@PathVariable ("nom") String nom) {
		Optional<Admin> existeadmin= this.adminRepo.findByNomAdmin(nom);
				if ( existeadmin.isPresent()) {
					 return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);}
				
				else  {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	
	
	
	
	
/*	
	@GetMapping("get_admin/{prénom}")
	public ResponseEntity<Admin> getByPrenom(@PathVariable String prénom ){
		Optional<Admin> existeadmin=this.adminRepo.findByPrenomAdmin(prénom);
		if ( existeadmin.isPresent()) {
			return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}*/
	
	
	   @PostMapping("/client/add")
	    public void ajouterClient(@RequestBody Client client) {
	        clientrepo.save(client);
		   
	    }
	
	
	   @PostMapping("/client/remove")
	    public void supprimerClient(@RequestBody Client client) {
		   clientrepo.delete(client);
	    }
	   
	   @PostMapping("/teams /add")
	    public void ajouterEquipe(@RequestBody EquipeTech equipeTech ) {
		   equipeTechRepo.save(equipeTech);
		   
	    }
	
	
	   @PostMapping("/teams/remove")
	    public void supprimerEquipe(@RequestBody EquipeTech equipeTech) {
		   equipeTechRepo.delete(equipeTech);
	    }
	    
	   
	   
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
