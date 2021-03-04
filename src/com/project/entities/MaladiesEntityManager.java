package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="maladies")
public class MaladiesEntityManager {

	@Id
	private int id_maladie;
	private String nom;
	
	public MaladiesEntityManager() {
		
	}

	public MaladiesEntityManager(int id_maladie, String nom) {
		super();
		this.id_maladie = id_maladie;
		this.nom = nom;
	}



	public int getId_maladie() {
		return id_maladie;
	}

	public void setId_maladie(int id_maladies) {
		this.id_maladie = id_maladies;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
