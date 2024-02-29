package com.gestion_des_interventions.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.gestion_des_interventions.model.Rôle;
import com.gestion_des_interventions.repository.RôleRepo;

@RestController
@RequestMapping("/roles")
public class RôleController {
	@Autowired
	private final RôleRepo rôleRepo ;
	
	public RôleController(RôleRepo rôleRepo ) {
		this.rôleRepo=rôleRepo;
	}
	

	  @GetMapping("all")
	    public List<Rôle> getAllRoles() {
	        return rôleRepo.findAll();
	    }
	  @GetMapping("/get_by_id/")
		public ResponseEntity<Rôle> getdByid(@RequestParam Long id) {
		    Optional<Rôle> roleexiste = this.rôleRepo.findById(id);
		    if (roleexiste.isPresent()) {
	         Rôle role  = roleexiste.get();
		        return new ResponseEntity<>(role, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}}
	  @DeleteMapping("/delet_role_by_id/{id}")
		public ResponseEntity<String> supprimerRole(@PathVariable Long id) {
		    Optional<Rôle> roleexiste = this.rôleRepo.findById(id);
		    if (roleexiste.isPresent()) {
		        this. rôleRepo.deleteById(id);
		        return new ResponseEntity<>("Role with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("Role  with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}
		@DeleteMapping("/delet_all_client")
		public   ResponseEntity<Void> supprimerAllRoles() {
			this.rôleRepo.deleteAll();
			return ResponseEntity.noContent().build();
			//return ResponseEntity<>("all users  were successfully deleted",HttpStatus.)); //method is used to return an HTTP 204 No Content status code, indicating that the delete
			}
		@PostMapping("/add_role")
		public ResponseEntity<String> ajouterClient (@RequestBody Rôle role ) {
			
			
			if (this.rôleRepo.existsById(role.getId())) {
				return 
					new ResponseEntity<>("client with id  " + role.getId() + " already exists.", HttpStatus.CONFLICT); 	}
			else {
			Rôle nv= new Rôle ();
			nv.setType(role.getType());		
			
			this.rôleRepo.save(nv);
			return new ResponseEntity<>("client with id  " + role.getId() + " added successfully.", HttpStatus.CREATED);
			}
			}
}
