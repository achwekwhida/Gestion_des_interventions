package com.gestion_des_interventions.model;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Réclamation")
public class Reclamation {
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String état;
	    private String descriptionDeProbléme;
	    private String type ;
	    private LocalDateTime date ;
	    
	    @ManyToOne
	    private Client client;
	    @ManyToOne
	    private Admin admin;
	    @ManyToMany
	    Set<Produit> produit;
	    
		public Reclamation() {
			super();
		}
		public Reclamation(Long id, String état, String description, String type, LocalDateTime date) {
			super();
			this.id = id;
			this.état = état;
			this.descriptionDeProbléme = description;
			this.type = type;
			this.date = date;
		}
		
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getÉtat() {
			return état;
		}
		public void setÉtat(String état) {
			this.état = état;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public LocalDateTime getDate() {
			return date;
		}
		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		public Client getClient() {
			return client;
		}
		public void setClient(Client client) {
			this.client = client;
		}
		public String getDescriptionDeProbléme() {
			return descriptionDeProbléme;
		}
		public void setDescriptionDeProbléme(String descriptionDeProbléme) {
			this.descriptionDeProbléme = descriptionDeProbléme;
		}
		public Admin getAdmin() {
			return admin;
		}
		
		public void setAdmin(Admin admin) {
			this.admin = admin;
			}
		}
		

