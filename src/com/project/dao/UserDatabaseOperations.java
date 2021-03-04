package com.project.dao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.AdminEntityManager;
import com.project.entities.MaladiesEntityManager;
import com.project.entities.UserEntityManager;


public class UserDatabaseOperations {
	
	 private static final String PERSISTENCE_UNIT_NAME = "FinalProject";   
	    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
	 
	    public static boolean createNewUser(String name, String email, int num_tel, String adresse, String password, String username, String gender,
				String day, String month, String year, String city, int id_maladie) {
	        if(!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	        
	        UserEntityManager newUserObj = new UserEntityManager();
	        MaladiesEntityManager maladie =  new MaladiesEntityManager();
	        maladie = DatabaseOperations.maladieDetailsById(id_maladie);
	        newUserObj.setName(name);
	        newUserObj.setEmail(email);
	        newUserObj.setNum_tel(num_tel);
	        newUserObj.setAdresse(adresse);
	        newUserObj.setPassword(password);
	        newUserObj.setUsername(username);
	        newUserObj.setGender(gender);
	        newUserObj.setDay(day);
	        newUserObj.setMonth(month);
	        newUserObj.setYear(year);
	        newUserObj.setCity(city);
	        newUserObj.setMaladie(maladie);
	        entityMgrObj.persist(newUserObj);
	        transactionObj.commit();
	        return true; 
	    }
	    

	    public static boolean isusernamePresent(String username) {
	        Query queryObj = entityMgrObj.createQuery("SELECT u FROM UserEntityManager u WHERE u.username = :username");
	        queryObj.setParameter("username",username);
	        try { 

	        	UserEntityManager selectedUser = (UserEntityManager) queryObj.getSingleResult();
	        	return(true);
	        }
	        catch(NoResultException e){
	            return(false);
	        }
	    }
	    
	    
	    public static boolean checkuserlogin(String username , String password) {
	    	System.out.println(username+ " " +password);
	        Query queryObj = entityMgrObj.createQuery("SELECT u FROM UserEntityManager u WHERE u.username = :username AND u.password= :password");
	        queryObj.setParameter("username",username);
	        queryObj.setParameter("password",password);
	        try { 

	        	UserEntityManager user = (UserEntityManager) queryObj.getSingleResult(); 
	        	return(true);
	        }
	        catch(NoResultException e){
	            return(false);
	        }
	    }
	    
	    
	    public static UserEntityManager getuserdetails(String username , String password) {
	    	System.out.println(username+ " " +password);
	        Query queryObj = entityMgrObj.createQuery("SELECT u FROM UserEntityManager u WHERE u.username = :username AND u.password= :password");
	        queryObj.setParameter("username",username);
	        queryObj.setParameter("password",password);
	        try { 

	        	UserEntityManager user = (UserEntityManager) queryObj.getSingleResult(); 
	        	return(user);
	        }
	        catch(NoResultException e){
	        	UserEntityManager user = new UserEntityManager();
	            return(user);
	        }
	    }
	    
	    
	    
	    public static boolean UpdateUser(UserEntityManager updateUserObj) {
	        if(!transactionObj.isActive()) {
	            transactionObj.begin();
	        }
	        
	        UserEntityManager updateUser =( UserEntityManager) updateUserObj;
	       /* MaladiesEntityManager maladie =  new MaladiesEntityManager();
	        maladie = DatabaseOperations.maladieDetailsById(id_maladie);
	        updateUserObj.setName(name);
	        updateUserObj.setEmail(email);
	        updateUserObj.setNum_tel(num_tel);
	        updateUserObj.setAdresse(adresse);
	        updateUserObj.setPassword(password);
	        updateUserObj.setUsername(username);
	        updateUserObj.setGender(gender);
	        updateUserObj.setDay(day);
	        updateUserObj.setMonth(month);
	        updateUserObj.setYear(year);
	        updateUserObj.setCity(city);
	        updateUserObj.setMaladie(maladie);*/
	        entityMgrObj.merge(updateUser);
	        transactionObj.commit();
	        return true; 
	    }
	    
}
