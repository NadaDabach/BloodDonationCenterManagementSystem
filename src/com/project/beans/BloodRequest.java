package com.project.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import com.project.dao.BloodRequestDAO;
import com.project.dao.DatabaseOperations;
import com.project.entities.UserEntityManager;

@ManagedBean(name="BloodRequest")
public class BloodRequest {

	private int id_demande;
	private String group_sanguin;
	private int quantite;
	private String username;
	
	public BloodRequest(int id_demande, String bloodGroup, int quantity) {
		super();
		this.id_demande = id_demande;
		this.group_sanguin = bloodGroup;
		this.quantite = quantity;
	}

	public int getId_demande() {
		return id_demande;
	}

	public void setId_demande(int id_demande) {
		this.id_demande = id_demande;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BloodRequest() {
		
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

	public List RequestsListFromDb() {
        return BloodRequestDAO.getAllRequestsDetails();        
    }
	
    public String addNewRequest(BloodRequest BloodRequest) {
    	HttpSession session = SessionUtils.getSession();
		UserEntityManager us = (UserEntityManager) session.getAttribute("user"); 
        return BloodRequestDAO.createNewRequest(BloodRequest.getQuantite(),BloodRequest.getGroup_sanguin(),us);
    }
}
