package com.project.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.DonorEntityManager;
import com.project.entities.StockEntityManager;
import com.project.entities.UserEntityManager;

public class BloodStockDAO {


	 private static final String PERSISTENCE_UNIT_NAME = "FinalProject";   
	    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	    
	    public static int quantitebygroup (String bloodGroup) {
	        Query queryObj = entityMgrObj.createQuery("SELECT d.quantite FROM StockEntityManager d WHERE d.bloodGroup = :bloodGroup");
	        queryObj.setParameter("bloodGroup",bloodGroup);
	        int quantite = Integer.parseInt(queryObj.getSingleResult().toString());
	        System.out.println(queryObj.getSingleResult().toString());
	        return quantite;
	    }
	    

	    public static List getAllBloodGroupQuantite() { 
	        Query queryObj = entityMgrObj.createQuery("SELECT d FROM StockEntityManager d");
	        List quantitelist = queryObj.getResultList();
	        if (quantitelist != null && quantitelist.size() > 0) {           
	            return quantitelist;
	        } else {
	            return null;
	        }
	    }
	    

	    private static boolean isbloodgroupdPresent(String bloodGroup) {
	        Query queryObj = entityMgrObj.createQuery("SELECT d FROM StockEntityManager d WHERE d.bloodGroup = :bloodGroup");
	        queryObj.setParameter("bloodGroup",bloodGroup);
	        try { 

	        	StockEntityManager selectebloodgroup = (StockEntityManager) queryObj.getSingleResult();
	        	return(true);
	        }
	        catch(NoResultException e){
	            return(false);
	        }
	    }
	    
	    
	    public static void edit_QuantiteStock(int quantite,String bloodGroup) {
	        if (!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	        if(isbloodgroupdPresent(bloodGroup)) {
	            Query queryObj = entityMgrObj.createQuery("UPDATE StockEntityManager d SET d.quantite=:quantite WHERE d.bloodGroup=:bloodGroup");
	        
	            queryObj.setParameter("bloodGroup", bloodGroup);
	            queryObj.setParameter("quantite", quantite);
	            int updateCount = queryObj.executeUpdate();
	            if(updateCount > 0) {
	                System.out.println("Record For Id: " + bloodGroup + " Is Updated");
	            }
	        }
	        transactionObj.commit();
	    }
}
