package com.project.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.project.entities.UserEntityManager;

public class LoginDAO {

	private static final String PERSISTENCE_UNIT_NAME = "FinalProject";   
    private static EntityManager entityMgrObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    private static EntityTransaction transactionObj = entityMgrObj.getTransaction();
    
    public static boolean validateUserLogin(String username , String password) {
    	System.out.println(username+ " " +password);
        Query queryObj = entityMgrObj.createQuery("SELECT u FROM UserEntityManager u WHERE u.username = :username AND u.password= :password");
        queryObj.setParameter("username",username);
        queryObj.setParameter("password",password);
        try { 

        	UserEntityManager user = (UserEntityManager) queryObj.getSingleResult(); 
        	return(true);
        }
        catch(NoResultException e){
        	System.out.println("Login error -->" + e.getMessage());
            return(false);
        }
    }
}
