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


import com.gestion_des_interventions.model.Client;
import com.gestion_des_interventions.repository.ClientRepo;


@RestController
@RequestMapping("/clients")
public class ClientController {

	
	@Autowired
	private final ClientRepo clientRepo;
	
	public ClientController(ClientRepo clientRepo) {
		this .clientRepo=clientRepo;
	}

	@GetMapping("all")
	public List<Client> getAll(){
		return this.clientRepo.findAll();
		}
	/*@GetMapping("/get_by_name/{name}")
	public Optional <Client> afficherParNom( @PathVariable String nom){
		return clientRepo.findByNomClient(nom);
		}
	/*@GetMapping("/get_by_lastname")
	public Optional <Client> afficherParPrenom( @RequestParam String prenom){
		return clientRepo.findByNom(prenom);
	}
	/*@GetMapping("/get_by_dateOfBirth")
	public Optional <Client> afficherParDateDeNaissance( @RequestParam Date dateDeNaissance){
		return clientRepo.findByDateDeNaissance(dateDeNaissance);}
	*/
	
	//@Requestparam
		@GetMapping("/get_by_id/")
		public ResponseEntity<Client> getdByid(@RequestParam Long id) {
		    Optional<Client> clientexiste = this.clientRepo.findById(id);
		    if (clientexiste.isPresent()) {
		        Client client = clientexiste.get();
		        return new ResponseEntity<>(client, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}}
		
		//@@PathVariabl
		@GetMapping("/get_by_id/{id}")
		public ResponseEntity<Client> findByid(@PathVariable Long id) {
		    Optional<Client> clientexiste = this.clientRepo.findById(id);
		    if (clientexiste.isPresent()) {
		        Client client = clientexiste.get();
		        return new ResponseEntity<>(client, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		}
	    
		
		//delete by id @variable
		@DeleteMapping("/delet_client_by_id/{id}")
		public ResponseEntity<String> supprimerClient(@PathVariable Long id) {
		    Optional<Client> clientexiste = this.clientRepo.findById(id);
		    if (clientexiste.isPresent()) {
		        this.clientRepo.deleteById(id);
		        return new ResponseEntity<>("Client with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("Client with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}

		//delete by id @param
		@DeleteMapping("/delet_clien_by_id/")
		public ResponseEntity<String> suppClient(@RequestParam Long id) { 
		    if (this.clientRepo.existsById(id)) {
		        this.clientRepo.deleteById(id);
		        return new ResponseEntity<>("Admin with ID " + id + " was successfully deleted.", HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>("Admin with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
		    }
		}
		
		// delete all 
		@DeleteMapping("/delet_all_client")
		public   ResponseEntity<Void> supprimerAllClient() {
			this.clientRepo.deleteAll();
			return ResponseEntity.noContent().build();
			//return ResponseEntity<>("all users  were successfully deleted",HttpStatus.)); //method is used to return an HTTP 204 No Content status code, indicating that the delete
			}

		
		
		@PostMapping("/add_client")
		public ResponseEntity<String> ajouterClient (@RequestBody Client client ) {
			
			
			if (this.clientRepo.existsById(client.getId())) {
				return 
					new ResponseEntity<>("client with id  " + client.getId() + " already exists.", HttpStatus.CONFLICT); 	}
			else {
			Client nv= new Client ();
			nv.setDateDeNaissance(client.getDateDeNaissance());		
			nv.setEmail(client.getEmail());
			nv.setId(client.getId());
			nv.setNom(client.getNom());
			nv.setPrénom(client.getPrénom());
			nv.setSexe(client.getSexe());
			nv.setTel(client.getTel());
			nv.setMdp(client.getMdp());
			
			this.clientRepo.save(nv);
			return new ResponseEntity<>("client with id  " + client.getId() + " added successfully.", HttpStatus.CREATED);
			}
			}
		
		@PostMapping("/add_client/{id}")
		public ResponseEntity<String> ajoutClient (@PathVariable Long id , @RequestParam String nom, @RequestParam String prénom, @RequestParam String email,
				@RequestParam String tel,@RequestParam String sexe,@RequestParam Date dateDeNaissance,@RequestParam String mdp){
			Optional<Client> clexiste =clientRepo.findById(id);
			if (clexiste.isPresent()) {
				return null ;}
				else {
					Client client= new Client( );
					 this.clientRepo.save(client);
					return new ResponseEntity<>("client with id  " + client.getId() + " added successfully.", HttpStatus.CREATED);
				}
			
		}

		
		@PutMapping("/update_client/{iid}")
		public Client updateClient (@PathVariable("iid") Long id , @RequestBody Client client) {
			
			Optional<Client > existecl = clientRepo.findById(id);
	        if (existecl.isPresent()) {
	        	Client nv=existecl.get();
	        	nv.setDateDeNaissance(client.getDateDeNaissance());
	        	nv.setEmail(client.getEmail());
	        	nv.setMdp(client.getMdp());
	        	nv.setNom(client.getNom());
	        	nv.setPrénom(client.getPrénom());
	        	nv.setSexe(client.getSexe());
	        	nv.setTel(client.getTel());
	        	return this.clientRepo.save(nv);
	        	}
	        else throw new NoSuchElementException("Client not found with that ID: " + client.getId());
	         
		}
		
		
		
		// Méthode pour afficher le nom d'un compte
		@GetMapping("/get_by/{id}/name")
		public ResponseEntity<String> getNomById(@PathVariable Long id) {
		    Optional<Client> existecl = clientRepo.findById(id);
		    if (existecl.isPresent()) {
		        return new ResponseEntity<>( "the name of client is : "+existecl.get().getNom(), HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>( "client not found with that id "+ id ,HttpStatus.NOT_FOUND);
		    }
		}
		
		
		@GetMapping("/get_by_id/{id}/lastname")
		public ResponseEntity<String> findPrénomById(@PathVariable Long id) {
	        Optional<Client> existcl = clientRepo.findById(id);
	        if (existcl.isPresent() ) {
	        	 return new ResponseEntity<>("the last name of client is :"+existcl.get().getPrénom(), HttpStatus.OK);     
	        	}
	        else {
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);
	        }
	        }
		
		
		@GetMapping("/get_by_id/{id}/email")
		public ResponseEntity <String> getEmailById(@PathVariable Long id ) {
	        Optional<Client> existcl = clientRepo.findById(id);
	        if (existcl.isPresent() ) {
	        	 return new ResponseEntity<>("the e-mail of client is : "+existcl.get().getEmail(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);
	       }
		

		@GetMapping("/get_by_id/{id}/phone_number")
		public ResponseEntity <String> getTelById(@PathVariable Long id ) {
	        Optional<Client> existcl = clientRepo.findById(id);
	        if (existcl.isPresent() ) {
	        	 return new ResponseEntity<>("the phone number of clien is :"+existcl.get().getTel(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/sexe")
		public ResponseEntity <String> getSexeById(@PathVariable Long id ) {
	        Optional<Client> existcl = clientRepo.findById(id);
	        if (existcl.isPresent() ) {
	        	 return new ResponseEntity<>("the sexe of client is :"+existcl.get().getSexe(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }
		
		
		
		
		@GetMapping("/get_by_id/{id}/Date_of_birth")
		public ResponseEntity <Date> getDateById(@PathVariable Long id ) {
	        Optional<Client> existclient= clientRepo.findById(id);
	        if (existclient.isPresent() ) {
	        	 return new ResponseEntity<>( existclient.get().getDateDeNaissance(), HttpStatus.OK);     
	        	}
	        else 
	        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);  }
	
}
