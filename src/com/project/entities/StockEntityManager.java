package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stock")
public class StockEntityManager {
	
	@Id
	private String bloodGroup;
	private int quantite;
	
	public StockEntityManager() {
		
	}
	
	public StockEntityManager(String bloodGroup, int quantite) {
		super();
		this.bloodGroup = bloodGroup;
		this.quantite = quantite;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	
	
	
}
