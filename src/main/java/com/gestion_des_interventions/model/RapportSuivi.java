package com.gestion_des_interventions.model;




import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class RapportSuivi {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private String état ;
	    @OneToMany(mappedBy = "rapportSuivi", cascade = CascadeType.ALL)
	    private List<Intervention> interventions;
	    
	    @OneToOne
	    private Admin admin;
	   
		public RapportSuivi() {
			super();
		}

		public RapportSuivi(Long id, String état) {
			super();
			this.id = id;
			this.état = état;
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
	    
}
