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
import com.gestion_des_interventions.model.Reclamation;
import com.gestion_des_interventions.repository.ClientRepo;
import com.gestion_des_interventions.repository.RecRepo;


@RestController
@RequestMapping("api/clients")
public class ClientController {

	
	@Autowired
	private  ClientRepo clientRepo;
	@Autowired
	private  RecRepo recRepo;
	
	


	@GetMapping("get_all")
	public List<Client> getAll(){
		return this.clientRepo.findAll();
		}
	
	/*@GetMapping("get_client/{nom}")
	public  ResponseEntity<Client> getByNom(@PathVariable ("nom") String nom) {
		Optional<Client> existeClient= this.clientRepo.fi;
				if ( existeClient.isPresent()) {
					 return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);}
				
				else  {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
	}*/
	
	@GetMapping("get_client/{prénom}")
	public ResponseEntity<Client> getByPrenom(@PathVariable String prénom ){
		Optional<Client> existeClient=this.clientRepo.findByPrénom(prénom);
		if ( existeClient.isPresent()) {
			return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{sexe}")
	public ResponseEntity<Client> getBySexe(@RequestParam String sexe ){
		Optional<Client> existeClient=this.clientRepo.findBySexe(sexe);
		if ( existeClient.isPresent()) {
			return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{civilité}")
	public ResponseEntity<Client> getByCivilité(@PathVariable String civilité ){
		Optional<Client> existeClient=this.clientRepo.findByCivilité(civilité);
		if ( existeClient.isPresent()) {
			return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{tel}")
	public ResponseEntity<Client> getByTel(@PathVariable String tel ){
		Optional<Client> existeClient=this.clientRepo.findByTel(tel);
		if ( existeClient.isPresent()) {
			return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("get_client/{active}")
	public ResponseEntity<Client> getByActive(@PathVariable Boolean active ){
		Optional<Client> existeClient=this.clientRepo.findByActive(active);
		if ( existeClient.isPresent()) {
			return new ResponseEntity<>(existeClient.get(), HttpStatus.OK);
		}
		else  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


		@GetMapping("/get_by_id/")
		public ResponseEntity<Client> getdByid(@RequestParam Long id) {
		    Optional<Client> clientexiste = this.clientRepo.findById(id);
		    if (clientexiste.isPresent()) {
		        Client client = clientexiste.get();
		        return new ResponseEntity<>(client, HttpStatus.OK);
		    } else {
		        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}}

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
		@GetMapping("/get_by_id/{id}/name")
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
	

		
@GetMapping("/get_by_id/{id}/civility")
public ResponseEntity <String> getcivilitéById(@PathVariable Long id ) {
    Optional<Client> existeadmin = clientRepo.findById(id);
    if (existeadmin.isPresent() ) {
    	return new ResponseEntity<>("the civility  of client is "+existeadmin.get().getCivilité(), HttpStatus.OK); 
    	}
    else 
    	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }


/*@GetMapping("/get_by_id/{id}/national_identity_card")
public ResponseEntity <String> getCinById(@PathVariable Long id ) {
    Optional<Client> existeadmin = clientRepo.findById(id);
    if (existeadmin.isPresent() ) {
    	return new ResponseEntity<>("the national identity card  of client is "+existeadmin.get().getCin(), HttpStatus.OK); 
    	}
    else 
    	return new ResponseEntity<>("client not found with that id "+ id,HttpStatus.NOT_FOUND);  }*/

@GetMapping("/get_by_id/{id}/national_identity_card")
public ResponseEntity <String> getCin(@PathVariable Long id ) throws Exception {
    Optional<Client> existeadmin = clientRepo.findById(id);
    if (existeadmin.isPresent() ) {
    	return new ResponseEntity<>("the national identity card  of client is "+existeadmin.get().getCin(), HttpStatus.OK); 
    	}
    else 
    	throw new Exception("client not found with that id : "+ id);
}

/*@GetMapping("/get_name")
public Client listetechbynom(String nom) {
	Client u =this.clientRepo.findByNom(nom);
	return u;
}*/


/*@GetMapping("/afficheravecnom/{nom}")
public List<Client> afficheravecnom( @PathVariable String nom){
	return this.clientRepo.findByNom(nom));
}*/
@GetMapping("/nom/{nom}")
public Client findByNom(@PathVariable String nom) {
	return  this.clientRepo.findBynom();
      
    }


	    
		
	
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
		public   ResponseEntity<String> supprimerAllClient() {
			this.clientRepo.deleteAll();
			
			return new ResponseEntity<>("all users  were successfully deleted",HttpStatus.OK); 
			}

		
		
		@PostMapping("/add_client")
		public ResponseEntity<String> ajouterClient (@RequestBody Client client ) throws Exception {
			
			Optional<Client> existeClient = this.clientRepo.findByEmail(client.getEmail());

			
			if (this.clientRepo.existsById(client.getId())) {
				return new ResponseEntity<>("client with id  " + client.getId() + " already exists.", HttpStatus.CONFLICT); 	
				}
			 	
			else if(existeClient.isPresent()) {
					//return new ResponseEntity<>("please change this email , there is an acount created with that email "+client.getEmail(),HttpStatus.CONFLICT);
			   throw  new Exception ("please change this email , there is an acount created with that email "+client.getEmail());	    
			}
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
			nv.setCin(client.getCin());
			nv.setCivilité(client.getCivilité());
			this.clientRepo.save(nv);
			return new ResponseEntity<>("client with id  " + client.getId() + " added successfully.", HttpStatus.CREATED);
			}
			}

		
		@PutMapping("/update_client/{id}")
		public ResponseEntity<String >updateClient (@PathVariable  Long id , @RequestBody Client client) {
			Optional<Client > existeClient = clientRepo.findById(id);
			if (existeClient.isPresent()) {
	        	Client nv=existeClient.get();

	        	nv.setDateDeNaissance(client.getDateDeNaissance());
	        	nv.setEmail(client.getEmail());
	        	nv.setMdp(client.getMdp());
	        	nv.setNom(client.getNom());
	        	nv.setPrénom(client.getPrénom());
	        	nv.setSexe(client.getSexe());
	        	nv.setTel(client.getTel());
	        	nv.setMdp(client.getMdp());
				nv.setCin(client.getCin());
				nv.setCivilité(client.getCivilité());
	             this.clientRepo.save(nv);
	             return new ResponseEntity<> ("client  with ID: "+ id +"updated successfully",HttpStatus.OK);
        	}
        else
        return new ResponseEntity<> ("client not found with ID: "+ id ,HttpStatus.CONFLICT);
		}
		
		
		
}





