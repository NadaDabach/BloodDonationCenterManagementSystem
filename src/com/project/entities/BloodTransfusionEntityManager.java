package com.project.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import com.project.beans.Donor;
import com.project.beans.Maladies;
import com.project.dao.BloodStockDAO;

/*import com.project.beans.JoinColumn;
import com.project.beans.ManyToOne;*/

@Entity
@Table(name="transfusionsanguine")
public class BloodTransfusionEntityManager {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id_transfusion;
	private Date dateTransfusion;
	private int quantite;
	
	@ManyToOne
	@JoinColumn(name = "id_maladie")
	private MaladiesEntityManager maladie; 
	
	@ManyToOne
	@JoinColumn(name = "donorId")
	private DonorEntityManager donor;
	
	@PostPersist
	public void newTransfusionAdded() {		
	    int oldquantity = BloodStockDAO.quantitebygroup(donor.getBloodGroup());
	    BloodStockDAO.edit_QuantiteStock((oldquantity+quantite),donor.getBloodGroup());
	    
	} 
	
	public BloodTransfusionEntityManager() {
		
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

	public void setDateTransfusion(Date dateTransfusion) {
		this.dateTransfusion = dateTransfusion;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public DonorEntityManager getDonor() { 
		return donor;
	}

	public void setDonor(DonorEntityManager donor) {
		this.donor = donor;
	}

	public MaladiesEntityManager getMaladie() {
		return maladie;
	}

	public void setMaladie(MaladiesEntityManager maladie) {
		this.maladie = maladie;
	}

}
