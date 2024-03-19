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

import com.gestion_des_interventions.model.Client;
import com.gestion_des_interventions.model.EquipeTech;
import com.gestion_des_interventions.repository.EquipeTechRepo;


@RestController
@RequestMapping("api/employee")
public class EquipeTechController {


	@Autowired
	private final EquipeTechRepo equipeTechRepo;
	
	public EquipeTechController(EquipeTechRepo equipeTechRepo) {
		this .equipeTechRepo=equipeTechRepo;
	}

	@GetMapping("get_all")
	public List<EquipeTech> getAll(){
		return this.equipeTechRepo.findAll();
		}
	
	/*@GetMapping("/get_by_dateOfBirth")
	public Optional <EquipeTech> afficherParDateDeNaissance( @RequestParam Date dateDeNaissance){
		return equipeTechRepo.findByDateDeNaissance(dateDeNaissance);}
	*/
	
	
	@GetMapping("get_admin/{nom}")
	public  ResponseEntity<EquipeTech> getByNom(@PathVariable ("nom") String nom) {
		Optional<EquipeTech> existeEmployee= this.equipeTechRepo.findByNom(nom);
				if ( existeEmployee.isPresent()) {
					 return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);}
				
				else  {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}
	
	@GetMapping("get_client/{prénom}")
	public ResponseEntity<EquipeTech> getByPrenom(@PathVariable String prénom ){
		Optional<EquipeTech> existeEmployee=this.equipeTechRepo.findByPrénom(prénom);
		if ( existeEmployee.isPresent()) {
			return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{sexe}")
	public ResponseEntity<EquipeTech> getBySexe(@PathVariable String sexe ){
		Optional<EquipeTech> existeEmployee=this.equipeTechRepo.findBySexe(sexe);
		if ( existeEmployee.isPresent()) {
			return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{civilité}")
	public ResponseEntity<EquipeTech> getByCivilité(@PathVariable String civilité ){
		Optional<EquipeTech> existeEmployee=this.equipeTechRepo.findByCivilité(civilité);
		if ( existeEmployee.isPresent()) {
			return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{tel}")
	public ResponseEntity<EquipeTech> getByTel(@PathVariable String tel ){
		Optional<EquipeTech> existeEmployee=this.equipeTechRepo.findByTel(tel);
		if ( existeEmployee.isPresent()) {
			return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{active}")
	public ResponseEntity<EquipeTech> getByActive(@PathVariable Boolean active ){
		Optional<EquipeTech> existeEmployee=this.equipeTechRepo.findByActive(active);
		if ( existeEmployee.isPresent()) {
			return new ResponseEntity<>(existeEmployee.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//@Requestparam
		@GetMapping("/get_by_id/")
		public ResponseEntity<EquipeTech> getdByid(@RequestParam Long id) {
		    Optional<EquipeTech> teamexiste = this.equipeTechRepo.findById(id);
		    if (teamexiste.isPresent()) {
		    	EquipeTech equipeTech = teamexiste.get();
		        return new ResponseEntity<>(equipeTech, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}}
		
		//@@PathVariabl
		@GetMapping("/get_by_id/{id}")
		public ResponseEntity<EquipeTech> findByid(@PathVariable Long id) {
		    Optional<EquipeTech> eqtexiste = this.equipeTechRepo.findById(id);
		    if (eqtexiste.isPresent()) {
		    	EquipeTech equipeTech = eqtexiste.get();
		        return new ResponseEntity<>(equipeTech, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}
	    
		
		//delete by id @variable
		@DeleteMapping("/delet_employee_by_id/{id}")
		public ResponseEntity<String> supprimerequipe(@PathVariable Long id) {
		    Optional<EquipeTech> eqtexiste = this.equipeTechRepo.findById(id);
		    if (eqtexiste.isPresent()) {
		        this.equipeTechRepo.deleteById(id);
		        return new ResponseEntity<>("employee with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("employee  with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}

		//delete by id @param
		@DeleteMapping("/delet_employee_by_id/")
		public ResponseEntity<String> suppequipe(@RequestParam Long id) { 
		    if (this.equipeTechRepo.existsById(id)) {
		        this.equipeTechRepo.deleteById(id);
		        return new ResponseEntity<>("employee with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("employee with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}
		
		// delete all 
		@DeleteMapping("/delet_all_employees")
		public   ResponseEntity<String> supprimerAllequipe() {
			this.equipeTechRepo.deleteAll();
			
		return new  ResponseEntity<>("all users  were successfully deleted",HttpStatus.OK);
			}

		
		
		@PostMapping("/add_employee")
		public ResponseEntity<String> ajouterequipe (@RequestBody EquipeTech equipeTech ) {
			Optional<EquipeTech> existeEmployee = this.equipeTechRepo.findByEmail(equipeTech.getEmail());
			
			if (this.equipeTechRepo.existsById(equipeTech.getId())) {
				return 
					new ResponseEntity<>("employee with id  " + equipeTech.getId() + " already exists.", HttpStatus.CONFLICT); 	}
			
			else if(existeEmployee.isPresent()) {
				return new ResponseEntity<>("please change this email , there is an acount created with that email "+equipeTech.getEmail(),HttpStatus.CONFLICT);
			    }
			
			else {
				EquipeTech nv= new EquipeTech ();
			nv.setDateDeNaissance(equipeTech.getDateDeNaissance());		
			nv.setEmail(equipeTech.getEmail());
			nv.setId(equipeTech.getId());
			nv.setNom(equipeTech.getNom());
			nv.setPrénom(equipeTech.getPrénom());
			nv.setSexe(equipeTech.getSexe());
			nv.setTel(equipeTech.getTel());
			nv.setMdp(equipeTech.getMdp());
			
			nv.setCin(equipeTech.getCin());
			nv.setCivilité(equipeTech.getCivilité());
			this.equipeTechRepo.save(nv);
			return new ResponseEntity<>("employee  with id  " + equipeTech.getId() + " added successfully.", HttpStatus.CREATED);
			}
			}
		

		
		@PutMapping("/update_employee/{iid}")
		public ResponseEntity<String> updateEquipe (@PathVariable("iid") Long id , @RequestBody EquipeTech equipeTech) {
			
			Optional<EquipeTech > existeteam = equipeTechRepo.findById(id);
	        if (existeteam.isPresent()) {
	        	EquipeTech nv=existeteam.get();
	        	nv.setDateDeNaissance(equipeTech.getDateDeNaissance());
	        	nv.setEmail(equipeTech.getEmail());
	        	nv.setMdp(equipeTech.getMdp());
	        	nv.setNom(equipeTech.getNom());
	        	nv.setPrénom(equipeTech.getPrénom());
	        	nv.setSexe(equipeTech.getSexe());
	        	nv.setTel(equipeTech.getTel());
	        	nv.setMdp(equipeTech.getMdp());
				nv.setCin(equipeTech.getCin());
				nv.setCivilité(equipeTech.getCivilité());
	        	 this.equipeTechRepo.save(nv);
	        	 
	       return new ResponseEntity<> ("employee  with ID: "+ id +"updated successfully",HttpStatus.OK);
        	}
        else
        	return new ResponseEntity<> ("employee not found with ID: "+ id ,HttpStatus.CONFLICT);
		}
		
		
		
		
		// Méthode pour afficher le nom d'un compte
		@GetMapping("/get_by/{id}/name")
		public ResponseEntity<String> getNomById(@PathVariable Long id) {
		    Optional<EquipeTech> existeteam = equipeTechRepo.findById(id);
		    if (existeteam.isPresent()) {
		        return new ResponseEntity<>( "the name of employee is : "+existeteam.get().getNom(), HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>( "employee not found with that id "+ id ,HttpStatus.NOT_FOUND);
		    }
		}
		
		
		@GetMapping("/get_by_id/{id}/lastname")
		public ResponseEntity<String> findPrénomById(@PathVariable Long id) {
	        Optional<EquipeTech> existeteam = equipeTechRepo.findById(id);
	        if (existeteam.isPresent() ) {
	        	 return new ResponseEntity<>("the last name of employee  is :"+existeteam.get().getPrénom(), HttpStatus.OK);     
	        	}
	        else {
	        	return new ResponseEntity<>("employee  not found with that id "+ id,HttpStatus.NOT_FOUND);
	        }
	        }
		
		
		@GetMapping("/get_by_id/{id}/email")
		public ResponseEntity <String> getEmailById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeadmin = equipeTechRepo.findById(id);
	        if (existeadmin.isPresent() ) {
	        	 return new ResponseEntity<>("the e-mail of employee is : "+existeadmin.get().getEmail(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("employee not found with that id "+ id,HttpStatus.NOT_FOUND);
	       }
		

		@GetMapping("/get_by_id/{id}/phone_number")
		public ResponseEntity <String> getTelById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam = equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>("the phone number of employee is :"+existeam.get().getTel(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("employee not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/sexe")
		public ResponseEntity <String> getSexeById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam= equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>("the sexe of employee is :"+existeam.get().getSexe(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("employee not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/Date_of_birth")
		public ResponseEntity <Date> getDateById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam = equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>( existeam.get().getDateDeNaissance(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }
		
		
@GetMapping("/get_by_id/{id}/civility")
public ResponseEntity <String> getcivilitéById(@PathVariable Long id ) {
    Optional<EquipeTech> existeadmin = equipeTechRepo.findById(id);
    if (existeadmin.isPresent() ) {
    	return new ResponseEntity<>("the civility  of employee is "+existeadmin.get().getCivilité(), HttpStatus.OK); 
    	}
    else 
    	return new ResponseEntity<>("employee not found with that id "+ id,HttpStatus.NOT_FOUND);  }	
		

@GetMapping("/get_by_id/{id}/national_identity_card")
public ResponseEntity <String> getcinById(@PathVariable Long id ) {
Optional<EquipeTech> existeadmin = equipeTechRepo.findById(id);
if (existeadmin.isPresent() ) {
return new ResponseEntity<>("the national identity card  of employee is "+existeadmin.get().getCin(), HttpStatus.OK); 
}
else 
return new ResponseEntity<>("employee not found with that id "+ id,HttpStatus.NOT_FOUND);  }	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
