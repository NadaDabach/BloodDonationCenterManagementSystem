package com.project.beans;

import javax.faces.bean.ManagedBean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.project.dao.DatabaseOperations;
import com.project.entities.DonorEntityManager;
 
@ManagedBean(name="Donor")
public class Donor {

	private int donorId;
	private String fullName;
	private String bloodGroup;
	private String city;
	private String email;
	private int phoneNumber;

	public Donor() {	
	}

	public Donor(int donorId, String fullName, String bloodGroup, String city, String email, int phoneNumber) {
		super();
		this.donorId = donorId;
		this.fullName = fullName;
		this.bloodGroup = bloodGroup;
		this.city = city;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Donor(String fullName, String bloodGroup, String city, String email, int phoneNumber) {
		super();
		this.fullName = fullName;
		this.bloodGroup = bloodGroup;
		this.city = city;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
	

	public String addNewDonor(Donor Donor) {
        return DatabaseOperations.createNewDonor(Donor.getFullName(),Donor.getBloodGroup(),Donor.getCity(),Donor.getEmail(),Donor.getPhoneNumber());
    }
    public String donordetailsbyid() {
    	FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        int selecteddonorID= Integer.parseInt(params.get("selecteddonorID"));
        DonorEntityManager donor = new DonorEntityManager();
        donor = DatabaseOperations.donorDetailsById(selecteddonorID);
    	sessionMap.put("editDonor", donor);
    	//setFullName(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("selectedname"));
        return "donorEdit.xhtml?faces-redirect=true";
    }
    

    public String upd_Donordetails(DonorEntityManager Donor) {
        return DatabaseOperations.edit_Donordetails(Donor.getDonorId(),Donor.getFullName(),Donor.getBloodGroup(),Donor.getCity(),Donor.getEmail(),Donor.getPhoneNumber());        
    }
    
	public List DonorListFromDb() {
        return DatabaseOperations.getAllDonorDetails();        
    }
	
 
	
}

