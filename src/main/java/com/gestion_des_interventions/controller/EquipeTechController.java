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

import com.gestion_des_interventions.model.EquipeTech;
import com.gestion_des_interventions.repository.EquipeTechRepo;


@RestController
@RequestMapping("api/team")
public class EquipeTechController {


	@Autowired
	private final EquipeTechRepo equipeTechRepo;
	
	public EquipeTechController(EquipeTechRepo equipeTechRepo) {
		this .equipeTechRepo=equipeTechRepo;
	}

	@GetMapping("all")
	public List<EquipeTech> getAll(){
		return this.equipeTechRepo.findAll();
		}
	/*@GetMapping("/get_by_name/{name}")
	public Optional <EquipeTech> afficherParNom( @PathVariable String nom){
		return equipeTechRepo.findByNomClient(nom);
		}
	/*@GetMapping("/get_by_lastname")
	public Optional <EquipeTech> afficherParPrenom( @RequestParam String prenom){
		return equipeTechRepo.findByNom(prenom);
	}
	/*@GetMapping("/get_by_dateOfBirth")
	public Optional <EquipeTech> afficherParDateDeNaissance( @RequestParam Date dateDeNaissance){
		return equipeTechRepo.findByDateDeNaissance(dateDeNaissance);}
	*/
	
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
		@DeleteMapping("/delet_team_by_id/{id}")
		public ResponseEntity<String> supprimerequipe(@PathVariable Long id) {
		    Optional<EquipeTech> eqtexiste = this.equipeTechRepo.findById(id);
		    if (eqtexiste.isPresent()) {
		        this.equipeTechRepo.deleteById(id);
		        return new ResponseEntity<>("person of teams with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("person of team with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}

		//delete by id @param
		@DeleteMapping("/delet_team_by_id/")
		public ResponseEntity<String> suppequipe(@RequestParam Long id) { 
		    if (this.equipeTechRepo.existsById(id)) {
		        this.equipeTechRepo.deleteById(id);
		        return new ResponseEntity<>("person of team with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("peron of team with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}
		
		// delete all 
		@DeleteMapping("/delet_all_teams")
		public   ResponseEntity<Void> supprimerAllequipe() {
			this.equipeTechRepo.deleteAll();
			return ResponseEntity.noContent().build();
			//return ResponseEntity<>("all users  were successfully deleted",HttpStatus.)); //method is used to return an HTTP 204 No Content status code, indicating that the delete
			}

		
		
		@PostMapping("/add_team")
		public ResponseEntity<String> ajouterequipe (@RequestBody EquipeTech equipeTech ) {
			
			
			if (this.equipeTechRepo.existsById(equipeTech.getId())) {
				return 
					new ResponseEntity<>("team' person  with id  " + equipeTech.getId() + " already exists.", HttpStatus.CONFLICT); 	}
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
			
			this.equipeTechRepo.save(nv);
			return new ResponseEntity<>("team's persson  with id  " + equipeTech.getId() + " added successfully.", HttpStatus.CREATED);
			}
			}
		
		@PostMapping("/add_team/{id}")
		public ResponseEntity<String> ajoutTeam (@PathVariable Long id , @RequestParam String nom, @RequestParam String prénom, @RequestParam String email,
				@RequestParam String tel,@RequestParam String sexe,@RequestParam Date dateDeNaissance,@RequestParam String mdp){
			Optional<EquipeTech> clexiste =equipeTechRepo.findById(id);
			if (clexiste.isPresent()) {
				return null ;}
				else {
					EquipeTech equipeTech= new EquipeTech( );
					 this.equipeTechRepo.save(equipeTech);
					return new ResponseEntity<>("team with id  " + equipeTech.getId() + " added successfully.", HttpStatus.CREATED);
				}
			
		}

		
		@PutMapping("/update_team/{iid}")
		public EquipeTech updateEquipe (@PathVariable("iid") Long id , @RequestBody EquipeTech equipeTech) {
			
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
	        	return this.equipeTechRepo.save(nv);
	        	}
	        else throw new NoSuchElementException("team' person not found with that ID: " + equipeTech.getId());
	         
		}
		
		
		
		// Méthode pour afficher le nom d'un compte
		@GetMapping("/get_by/{id}/name")
		public ResponseEntity<String> getNomById(@PathVariable Long id) {
		    Optional<EquipeTech> existeteam = equipeTechRepo.findById(id);
		    if (existeteam.isPresent()) {
		        return new ResponseEntity<>( "the name of team's person is : "+existeteam.get().getNom(), HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>( "team's person not found with that id "+ id ,HttpStatus.NOT_FOUND);
		    }
		}
		
		
		@GetMapping("/get_by_id/{id}/lastname")
		public ResponseEntity<String> findPrénomById(@PathVariable Long id) {
	        Optional<EquipeTech> existeteam = equipeTechRepo.findById(id);
	        if (existeteam.isPresent() ) {
	        	 return new ResponseEntity<>("the last name of team's person  is :"+existeteam.get().getPrénom(), HttpStatus.OK);     
	        	}
	        else {
	        	return new ResponseEntity<>("team's person  not found with that id "+ id,HttpStatus.NOT_FOUND);
	        }
	        }
		
		
		@GetMapping("/get_by_id/{id}/email")
		public ResponseEntity <String> getEmailById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeadmin = equipeTechRepo.findById(id);
	        if (existeadmin.isPresent() ) {
	        	 return new ResponseEntity<>("the e-mail of client is : "+existeadmin.get().getEmail(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);
	       }
		

		@GetMapping("/get_by_id/{id}/phone_number")
		public ResponseEntity <String> getTelById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam = equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>("the phone number of clien is :"+existeam.get().getTel(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/sexe")
		public ResponseEntity <String> getSexeById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam= equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>("the sexe of client is :"+existeam.get().getSexe(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/Date_of_birth")
		public ResponseEntity <Date> getDateById(@PathVariable Long id ) {
	        Optional<EquipeTech> existeam = equipeTechRepo.findById(id);
	        if (existeam.isPresent() ) {
	        	 return new ResponseEntity<>( existeam.get().getDateDeNaissance(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }
}
