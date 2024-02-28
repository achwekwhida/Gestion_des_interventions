package com.gestion_des_interventions.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping("/get_by_name/{name}")
	public Optional <Client> afficherParNom( @PathVariable String nom){
		return clientRepo.findByNom(nom);
		
	}
	@GetMapping("/get_by_lastname")
	public Optional <Client> afficherParPrenom( @RequestParam String prenom){
		return clientRepo.findByNom(prenom);
	}
	/*@GetMapping("/get_by_dateOfBirth")
	public Optional <Client> afficherParDateDeNaissance( @RequestParam Date dateDeNaissance){
		return clientRepo.findByDateDeNaissance(dateDeNaissance);}
	*/
}
