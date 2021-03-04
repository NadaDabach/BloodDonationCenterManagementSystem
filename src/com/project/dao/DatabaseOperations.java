package com.project.dao;

import java.util.List;
import java.sql.Timestamp;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
 
import com.project.beans.Donor;
import com.project.entities.AdminEntityManager;
import com.project.entities.BloodTransfusionEntityManager;
import com.project.entities.DonorEntityManager;
import com.project.entities.MaladiesEntityManager;
import com.project.entities.RequestEntityManager;
import com.project.entities.UserEntityManager;

 
public class DatabaseOperations {
 
    private static final String PERSISTENCE_UNIT_NAME = "FinalProject";   
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
 

    public static List getAllDonorDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT d FROM DonorEntityManager d");
        List donorList = queryObj.getResultList();
        if (donorList != null && donorList.size() > 0) {           
            return donorList;
        } else {
            return null;
        }
    }
 

    public static String createNewDonor(String fullName,String bloodGroup,String city,String email,int phoneNumber) {
        if(!transactionObj.isActive()) {
            transactionObj.begin();
        }
 
        DonorEntityManager newDonorObj = new DonorEntityManager();
        newDonorObj.setDonorId(getMaxDonorId());
        newDonorObj.setFullName(fullName);
        newDonorObj.setBloodGroup(bloodGroup);
        newDonorObj.setCity(city);
        newDonorObj.setEmail(email);
        newDonorObj.setPhoneNumber(phoneNumber);
        entityMgrObj.persist(newDonorObj);
        transactionObj.commit();
        return "indexAdmin.xhtml?faces-redirect=true"; 
    }
 
   
    // getting donorId from database
    public static DonorEntityManager donorDetailsById (int donorId) {
        Query queryObj = entityMgrObj.createQuery("SELECT d FROM DonorEntityManager d WHERE d.donorId = :donorId");
        queryObj.setParameter("donorId",donorId);
        DonorEntityManager donor = (DonorEntityManager) queryObj.getSingleResult();
        return donor;
    }
    

    private static boolean isdonorIdPresent(int donorId) {
        Query queryObj = entityMgrObj.createQuery("SELECT d FROM DonorEntityManager d WHERE d.donorId = :donorId");
        queryObj.setParameter("donorId",donorId);  
        try { 

        	DonorEntityManager selectedDonorId = (DonorEntityManager) queryObj.getSingleResult();
        	return(true);
        }
        catch(NoResultException e){
            return(false);
        }
    }
    

    public static String edit_Donordetails(int donorId,String fullName,String bloodGroup,String city,String email,int phoneNumber) {
        if (!transactionObj.isActive()) {
            transactionObj.begin();
        }
        if(isdonorIdPresent(donorId)) {
            Query queryObj = entityMgrObj.createQuery("UPDATE DonorEntityManager d SET d.fullName=:fullName , d.bloodGroup=:bloodGroup , d.city=:city , d.email=:email , d.phoneNumber=:phoneNumber WHERE d.donorId=:donorId");
            queryObj.setParameter("fullName", fullName);
            queryObj.setParameter("bloodGroup", bloodGroup);
            queryObj.setParameter("city", city);
            queryObj.setParameter("email", email);
            queryObj.setParameter("phoneNumber", phoneNumber);
            queryObj.setParameter("donorId", donorId);
            int updateCount = queryObj.executeUpdate();
            if(updateCount > 0) {
                System.out.println("Record For Id: " + donorId + " Is Updated");
            }
        }
        transactionObj.commit();
        FacesContext.getCurrentInstance().addMessage("editDonorForm:donorId", new FacesMessage("Donor Record #" + donorId + " Is Successfully Updated In Db"));
        return "indexAdmin.xhtml";
    }
    

    private static int getMaxDonorId() {
        int maxDonorId = 1;
        Query queryObj = entityMgrObj.createQuery("SELECT MAX(d.donorId)+1 FROM DonorEntityManager d");
        if(queryObj.getSingleResult() != null) {
            maxDonorId = (Integer) queryObj.getSingleResult();
        }
        return maxDonorId;
    }
    

    
    public static List getAllTransfusionDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT b FROM BloodTransfusionEntityManager b");
        List transfusionList = queryObj.getResultList();
        if (transfusionList != null && transfusionList.size() > 0) {           
            return transfusionList;
        } else {
            return null;
        }
    } 
 

    public static List getAllMaladiesDetails() {
        Query queryObj = entityMgrObj.createQuery("SELECT m FROM MaladiesEntityManager m");
        List maladiesList = queryObj.getResultList();
        if (maladiesList != null && maladiesList.size() > 0) {      
            return maladiesList;
        } else {
            return null;
        }
    }
    
    
    public static MaladiesEntityManager maladieDetailsById (int id_maladie) {
        Query queryObj = entityMgrObj.createQuery("SELECT m FROM MaladiesEntityManager m WHERE m.id_maladie = :id_maladie");
        queryObj.setParameter("id_maladie",id_maladie);
        MaladiesEntityManager maladie = (MaladiesEntityManager) queryObj.getSingleResult();
        return maladie;
    }
    

    
    public static String createNewTransfusion(int id_transfusion ,Date dateTransfusion, int quantite, int donorId, int id_maladie) {
        if(!transactionObj.isActive()) {
            transactionObj.begin();
        }
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        Timestamp ts =  new Timestamp(time);
        
        DonorEntityManager don =  new DonorEntityManager();
        don = donorDetailsById(donorId);
        
        MaladiesEntityManager maladie =  new MaladiesEntityManager();
        maladie = maladieDetailsById(id_maladie);
        
        BloodTransfusionEntityManager newTransfusionObj = new BloodTransfusionEntityManager();
        newTransfusionObj.setId_transfusion(getMaxTransfusionId());
        newTransfusionObj.setDateTransfusion(ts);
        newTransfusionObj.setDonor(don);
        newTransfusionObj.setMaladie(maladie);
        newTransfusionObj.setQuantite(quantite);
        entityMgrObj.persist(newTransfusionObj);
        transactionObj.commit();
        return "indexAdmin.xhtml?faces-redirect=true"; 
    }  
    

    private static int getMaxTransfusionId() {
        int maxTransfusionId = 1;
        Query queryObj = entityMgrObj.createQuery("SELECT MAX(b.id_transfusion)+1 FROM BloodTransfusionEntityManager b");
        try { 

        	maxTransfusionId = (Integer) queryObj.getSingleResult();
        	return(maxTransfusionId);
        }
        catch(NoResultException e){
            return(1);
        }

    }
    


    public static boolean checkadminlogin(String email , String password) {
    	System.out.println(email+ " " +password);
        Query queryObj = entityMgrObj.createQuery("SELECT a FROM AdminEntityManager a WHERE a.email = :email AND a.password= :password");
        queryObj.setParameter("email",email);
        queryObj.setParameter("password",password);
        try { 

        	AdminEntityManager admin = (AdminEntityManager) queryObj.getSingleResult(); 
        	return(true);
        }
        catch(NoResultException e){
            return(false);
        }
    }
    

    
    

}