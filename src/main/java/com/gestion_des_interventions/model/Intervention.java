package com.gestion_des_interventions.model;

import java.util.Date;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Intervention {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private Date dateDebut;
	    private Date dateFin;
	    private String état;
	    private String descriptionProbléme;
	    
	    
	    @ManyToOne 
	    private RapportSuivi rapportSuivi;
	    @ManyToOne
	    private Projet projet;
	    @OneToMany(mappedBy = "intervention")
	    private List<Tache> taches;
	    @ManyToOne
	    private Client client;
	    @ManyToOne
	    private Admin admin;
	    
	    
	    
	    
	    
	    
	    
	 
		public Intervention() {
			super();
		}
		public Intervention(Long id, Date dateDebut, Date dateFin, String état, String descriptionProbléme) {
			super();
			this.id = id;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.état = état;
			this.descriptionProbléme = descriptionProbléme;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Date getDateDebut() {
			return dateDebut;
		}
		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}
		public Date getDateFin() {
			return dateFin;
		}
		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}
		public String getÉtat() {
			return état;
		}
		public void setÉtat(String état) {
			this.état = état;
		}
		public String getDescriptionProbléme() {
			return descriptionProbléme;
		}
		public void setDescriptionProbléme(String descriptionProbléme) {
			this.descriptionProbléme = descriptionProbléme;
		}
	    
}
