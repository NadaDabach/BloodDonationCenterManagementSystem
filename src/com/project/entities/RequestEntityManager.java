package com.project.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="demandes")
public class RequestEntityManager {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id_demande;
	private String group_sanguin;
	private int quantite;
	private Timestamp date;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private UserEntityManager utilisateur; 
		
	public RequestEntityManager() {}
	
	public int getId_demande() {
		return id_demande;
	}
	public void setId_demande(int id_demande) {
		this.id_demande = id_demande;
	}
	
	public String getGroup_sanguin() {
		return group_sanguin;
	}

	public void setGroup_sanguin(String group_sanguin) {
		this.group_sanguin = group_sanguin;
	}
	
	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public UserEntityManager getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(UserEntityManager utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	
	
}
