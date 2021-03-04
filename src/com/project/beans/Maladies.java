package com.project.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="Maladies")
public class Maladies {
	
	private int id_maladie;
	private String nom;
	
	public Maladies() {
		
	}

	public Maladies(int id_maladie, String nom) {
		super();
		this.id_maladie = id_maladie;
		this.nom = nom;
	}



	public int getId_maladies() {
		return id_maladie;
	}

	public void setId_maladies(int id_maladies) {
		this.id_maladie = id_maladies;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
