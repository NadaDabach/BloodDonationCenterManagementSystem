package com.project.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.DonorEntityManager;
import com.project.entities.RequestEntityManager;
import com.project.entities.StockEntityManager;
import com.project.entities.UserEntityManager;

public class BloodRequestDAO {
	
	 private static final String PERSISTENCE_UNIT_NAME = "FinalProject";   
	    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	    
	   
	    public static List getAllRequestsDetails() {
	        Query queryObj = entityMgrObj.createQuery("SELECT r FROM RequestEntityManager r ");
	        List requestList = queryObj.getResultList();
	        if (requestList != null && requestList.size() > 0) {  
	            return requestList;
	        } else {
	            return null;
	        }
	    }
	    
	    private static int getMaxReuestsId() {
	        int maxDonorId = 1;
	        Query queryObj = entityMgrObj.createQuery("SELECT MAX(r.id_demande)+1 FROM RequestEntityManager r");
	        if(queryObj.getSingleResult() != null) {
	            maxDonorId = (Integer) queryObj.getSingleResult();
	        }
	        return maxDonorId;
	    }
	    
	    
	    public static String createNewRequest(int quantite,String group_sanguin,UserEntityManager utilisateur) {
	    	 Date date= new Date();
	         //getTime() returns current time in milliseconds
	         long time = date.getTime();
	         Timestamp ts =  new Timestamp(time);
	        if(!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	        RequestEntityManager newRequestObj = new RequestEntityManager();
	        newRequestObj.setId_demande(getMaxReuestsId());
	        newRequestObj.setGroup_sanguin(group_sanguin);
	        newRequestObj.setQuantite(quantite);
	        newRequestObj.setUtilisateur(utilisateur);
	        newRequestObj.setDate(ts);
	        entityMgrObj.persist(newRequestObj);
	        transactionObj.commit();
	        return "indexRequest.xhtml?faces-redirect=true";
	    }
	    
}
