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
import com.gestion_des_interventions.model.Produit;
import com.gestion_des_interventions.repository.ProduitRepo;

@RestController
@RequestMapping("api/products")
public class ProduitController {

    @Autowired
    private ProduitRepo produitRepo;
    

    @GetMapping("/get_all")
	public List<Produit> getAll(){
		return this.produitRepo.listDeProduitOrdoné();
	}
	
	//@Requestparam
	@GetMapping("/get_by_id/")
	public ResponseEntity<Produit> getProduitByid(@RequestParam Long id) {
	    Optional<Produit> produitexiste = this.produitRepo.findById(id);
	    if (produitexiste.isPresent()) {
	    	Produit produit = produitexiste.get();
	        return new ResponseEntity<>(produit, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}}
	
@GetMapping("/get_by_id/{id}")
	
public ResponseEntity<Produit> getProduit(@PathVariable Long id) {
	Optional<Produit> produitexiste = this.produitRepo.findById(id);
    if (produitexiste.isPresent()) {
    	Produit produit = produitexiste.get();
        return new ResponseEntity<>(produit, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}}

@GetMapping("get_product/{nom}")
public  ResponseEntity<Produit> getByType(@PathVariable ("nom") String nom) {
	Optional<Produit> existeProduit= this.produitRepo.findByNom(nom);
			if ( existeProduit.isPresent()) {
				 return new ResponseEntity<>(existeProduit.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
@GetMapping("get_product/{date_de_lancement}")
public  ResponseEntity<Produit> getByDateDeLancement(@PathVariable("date_de_lancement")Date date) {
	Optional<Produit> existeProduit= this.produitRepo.findByDateDeLancement(date);
			if ( existeProduit.isPresent()) {
				 return new ResponseEntity<>(existeProduit.get(), HttpStatus.OK);}
			
			else  {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);}}
	
	//@@PathVariabl
	@DeleteMapping("/delet_product_by_id/{id}")
	
	public ResponseEntity<String> supprimerProduit(@PathVariable Long id) { 
	    if (this.produitRepo.existsById(id)) {
	        this.produitRepo.deleteById(id);
	        return new ResponseEntity<>("product with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("product  with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}

	//delete by id @param
	@DeleteMapping("/delet_product_by_id/")
	public ResponseEntity<String> suppProduit(@RequestParam Long id) { 
	    if (this.produitRepo.existsById(id)) {
	        this.produitRepo.deleteById(id);
	        return new ResponseEntity<>("product with ID " + id + " was successfully deleted.", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("product with ID " + id + " was not found.", HttpStatus.NOT_FOUND);
	    }
	}
	

	
	// delete all 
	@DeleteMapping("/delet_all_products")
	public   ResponseEntity<String> supprimerProduit() {
		this.produitRepo.deleteAll();
		return  new ResponseEntity<>("all products were successfully deleted",HttpStatus.OK); 
		}

	
	
	@PostMapping("/add_product")
	public ResponseEntity<String> ajouterProduit(@RequestBody Produit produit ) {
		
		
		if (this.produitRepo.existsById(produit.getId())) {
			return new ResponseEntity<>("produit with id  " + produit.getId() + " already exists.", HttpStatus.CONFLICT); 	}
		else {
			Produit nv= new Produit ();
		nv.setId(produit.getId());		
		nv.setDateDeLancement(produit.getDateDeLancement());
		nv.setNom(produit.getNom());
		this.produitRepo.save(nv);
		
		return new ResponseEntity<>("product with id  " + produit.getId() + " added successfully.", HttpStatus.CREATED);
		}
		}
	
	@PostMapping("/add_product/{id}")
	public ResponseEntity<String> ajoutProduit (@PathVariable Long id , @RequestParam String  titr, @RequestParam Date datedelancemment){
		Optional<Produit> produitexiste =produitRepo.findById(id);
		if (produitexiste.isPresent()) {
			return null ;}
			else {
				Produit produit= new Produit( );
				 this.produitRepo.save(produit);
				return new ResponseEntity<>("product with id  " + produit.getId() + " added successfully.", HttpStatus.CREATED);
			}
		
	}

	
	@PutMapping("/update_product/{iid}")
	public ResponseEntity<String> updateproduit (@PathVariable("iid") Long id , @RequestBody Produit produit) {
		
		Optional<Produit > produitexiste = produitRepo.findById(id);
		if (produitexiste.isPresent()) {
			Produit nv=produitexiste.get();
			nv.setNom(produit.getNom());		
			nv.setDateDeLancement(produit.getDateDeLancement());
		
			
        	 return new ResponseEntity<> ("product  with ID: "+ id + " updated successfully",HttpStatus.OK);
        	}
        else
        return new ResponseEntity<> ("product  not found with ID: "+ id ,HttpStatus.CONFLICT);
        }
	
	
	
	
	// Méthode pour afficher le nom d'un compte
	@GetMapping("/get_by_id/{id}/date_lancemment")
	public ResponseEntity<String> getDatedelancementyId(@PathVariable Long id) {
	    Optional<Produit> produitexiste = produitRepo.findById(id);
	    if (produitexiste.isPresent()) {
	        return new ResponseEntity<>("la date de lancemment de ce produit est :" +produitexiste.get().getDateDeLancement(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(" product not found with that id "+ id,HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@GetMapping("/get_by_id/{id}/title")
	public ResponseEntity<String> findTitleyId(@PathVariable Long id) {
        Optional<Produit> produitexiste = produitRepo.findById(id);
        if (produitexiste.isPresent() ) {
        	 return new ResponseEntity<>("le titre de ce produit est  :"+produitexiste.get().getNom(), HttpStatus.OK);     
        	}
        else {
        	return new ResponseEntity<>("product not found with that id "+ id,HttpStatus.NOT_FOUND);
        }
        }
	
	
	}