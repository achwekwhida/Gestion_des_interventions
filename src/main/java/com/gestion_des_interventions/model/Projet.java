package com.gestion_des_interventions.model;

import java.util.Date;
import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Projet {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titre;
    private Date dateDeLancement;
    
    @OneToMany(mappedBy = "projet")
    private List<Intervention> interventions;

	public Projet() {
		super();
	}
	public Projet(Long id, String titre, Date dateDeLancement) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateDeLancement = dateDeLancement;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Date getDateDeLancement() {
		return dateDeLancement;
	}
	public void setDateDeLancement(Date dateDeLancement) {
		this.dateDeLancement = dateDeLancement;
	}
    

}
