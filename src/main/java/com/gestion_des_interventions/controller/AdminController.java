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
import com.gestion_des_interventions.repository.AdminRepo;


@RestController
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	private final AdminRepo adminRepo;
	
	public AdminController(AdminRepo adminRepo) {
		this .adminRepo=adminRepo;
	}
	
	@GetMapping("/all")
	public List<Admin> getAll(){
		return this.adminRepo.findAll();
	}
	
	// get by id 
	//@Requestparam
	@GetMapping("/get_by_id/")
	public Admin getByid(@RequestParam Long id ) {
		//return this.adminRepo.findById(id).get();
		 Optional<Admin> optionalAdmin = adminRepo.findById(id);
	        return optionalAdmin.orElse(null);
		}
	
	// get by id 
	//@@PathVariabl
	@GetMapping("/get_by_id/{id}")
	public Admin findByid(@PathVariable Long id ) {
		 Optional<Admin> a = this.adminRepo.findById(id) ;// Retourne null si le compte n'existe pas
		return a.orElse(null);
    }
	
	//delete by id @variable
	@DeleteMapping("/delet_admin_by_id/{id}")
	public String supprimerAdmin(@PathVariable Long id ) {
		this.adminRepo.deleteById(id);
		return "Admin was successfully deleted";
	}
	
	//delete by id @param
	@DeleteMapping("/delet_admin_by_id/")
	public String suppAdmin(@RequestParam Long  id ) {
		this.adminRepo.deleteById(id);
		return "Admin was successfully deleted";
		}
	
	// delete all 
	@DeleteMapping("/delet_all_admin")
	public   ResponseEntity<Void> supprimerAdmin() {
		this.adminRepo.deleteAll();
		return ResponseEntity.noContent().build(); //method is used to return an HTTP 204 No Content status code, indicating that the delete
		}

	//Créer un nouveau compte
	/*public Admin ajouterAdmin( @RequestBody Admin  admin ) {
		return this.adminRepo.save(admin);
	}*/
	@PostMapping("/add_admin")
	public ResponseEntity<Admin> ajouterUser (@RequestBody Admin admin ) {
		Optional<Admin> adminexiste=adminRepo.findById(admin.getId());
		
		if (adminexiste.isPresent()) {
			return null ; 	}
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
		return new ResponseEntity<>(nv, HttpStatus.CREATED);
		}
		}
	
	@PostMapping("/add_admin/{id}")
	public ResponseEntity<Admin> ajoutUser (@PathVariable Long id , @RequestParam String nom, @RequestParam String prénom, @RequestParam String email,
			@RequestParam String tel,@RequestParam String sexe,@RequestParam Date dateDeNaissance,@RequestParam String mdp){
		Optional<Admin> adminexiste =adminRepo.findById(id);
		if (adminexiste.isPresent()) {
			return null ;}
			else {
				Admin admin= new Admin( );
				Admin nv = adminRepo.save(admin);
				return new ResponseEntity<>(nv, HttpStatus.CREATED);
			}
		
	}
//Mettre à jour un compte existant
	
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
        else throw new NoSuchElementException("User not found with ID: " + admin.getId());
// else retrne new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Méthode pour afficher le nom d'un compte
	/*@GetMapping("/get_by/{id}/nom")
	public ResponseEntity<String>getNomById(@PathVariable Long id) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	new ResponseEntity<>(existeadmin.get().getNom(), HttpStatus.OK);}
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }*/
	
	
	/*@GetMapping("/get_by_id/{
 
  
    }/prénom")
	public ResponseEntity<String> findPrénomById(@PathVariable Long id) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	new ResponseEntity<>(existeadmin.get().getPrénom(), HttpStatus.OK);     
        	}
        else {
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }*/
	
	
	
	
	/*@GetMapping("/get_by_id/{id}/email")
	public ResponseEntity <String> getEmailById(@PathVariable Long id ) {
        Optional<Admin> existeadmin = adminRepo.findById(id);
        if (existeadmin.isPresent() ) {
        	new ResponseEntity<>(existeadmin.get().getEmail(), HttpStatus.OK);     
        	}
        else 
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
		
        }*/
	
	
	
	
	@GetMapping("get_admin/{nom}")
	public  ResponseEntity<Admin> getByNom(@PathVariable ("nom") String nom) {
		Optional<Admin> existeadmin= this.adminRepo.findByNom(nom);
				if ( existeadmin.isPresent()) {
					 return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);}
				
				else  {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	
	@GetMapping("get_admin/nom")
	public  ResponseEntity<Admin>  getBynom(@RequestParam String nom) {
		Optional<Admin> existeadmin=this.adminRepo.findBynom(nom);
				if ( existeadmin.isPresent()) {
					return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);
				}
				else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	
	}
	
	
	@GetMapping("get_admin/{prenom}")
	public ResponseEntity<Admin> getByPrenom(@PathVariable String prénom ){
		Optional<Admin> existeadmin=this.adminRepo.findByPrenom(prénom)
				 ;
		if ( existeadmin.isPresent()) {
			return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("get_admin/prénom")
	public ResponseEntity<Admin> getByprénom(@RequestParam String prénom ){
		Optional<Admin> existeadmin=this.adminRepo.findByPrenom(prénom);
		if ( existeadmin.isPresent()) {
			return new ResponseEntity<>(existeadmin.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
