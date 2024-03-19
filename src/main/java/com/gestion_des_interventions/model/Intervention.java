package com.gestion_des_interventions.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Intervention {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(nullable = false)
	    private Long id;

	    private Date dateDebut;
	    private Date dateFin;
	    private String état;
	    @Column(columnDefinition = "TEXT")
	    private String solution;
	    
	    
	    @ManyToOne 
	    private RapportSuivi rapportSuivi;
	  /*  @ManyToOne
	    private Admin admin;*/
	    @ManyToOne
	    private EquipeTech equipeTech;
	    
	    
	    
	    
	    
	    
	 
		public Intervention() {
			super();
		}
		public Intervention(Long id, Date dateDebut, Date dateFin, String état, String solution) {
			super();
			this.id = id;
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			this.état = état;
			this.solution = solution;
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
		public String getSolution() {
			return solution;
		}
		public void setSolution(String solution) {
			this.solution =solution;
		}
	    
}
