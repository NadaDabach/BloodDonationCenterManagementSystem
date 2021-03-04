package com.project.beans;

import java.util.Date;
import java.sql.Timestamp;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.project.dao.DatabaseOperations;

@ManagedBean(name="BloodTransfusion")
public class BloodTransfusion {
	
	private int id_transfusion;
	private Timestamp dateTransfusion;
	private int quantite;
	//private MaladiesEntityManager maladie;
	private int donorId;
	private int id_maladie;
	private List<Maladies> maladies;
	
	public BloodTransfusion() {
		maladies = DatabaseOperations.getAllMaladiesDetails();
	}
	
	public int getId_transfusion() {
		return id_transfusion;
	}

	public void setId_transfusion(int id_transfusion) {
		this.id_transfusion = id_transfusion;
	}

	public Date getDateTransfusion() {
		return dateTransfusion;
	}

	public void setDateTransfusion(Timestamp dateTransfusion) {
		this.dateTransfusion = dateTransfusion;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getDonorId() {
		return donorId;
	}

	public List<Maladies> getMaladies() {
		return maladies;
	}

	public void setMaladies(List<Maladies> maladies) {
		this.maladies = maladies;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}
	

	public int getId_maladie() {
		return id_maladie;
	}

	public void setId_maladie(int id_maladie) {
		this.id_maladie = id_maladie;
	}

    public String addNewTransfusion(BloodTransfusion BloodTransfusion) {
        return DatabaseOperations.createNewTransfusion(BloodTransfusion.getId_transfusion(),BloodTransfusion.getDateTransfusion(),BloodTransfusion.getQuantite(),BloodTransfusion.getDonorId(),BloodTransfusion.getId_maladie());
    }
    
	public List TransfusionListFromDb() {
        return DatabaseOperations.getAllTransfusionDetails();        
    }
}
