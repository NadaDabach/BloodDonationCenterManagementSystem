package com.project.beans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.project.dao.DatabaseOperations;

@ManagedBean(name="Admin")
public class Admin {
	
	private int id_admin;
	private String email;
	private String password;
	
	public Admin() {
		
	}
	
	public int getId_admin() {
		return id_admin;
	}
	
	public void setId_admin(int id_admin) {
		this.id_admin = id_admin;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	} 
	
	public String loginAdmin(Admin Admin) {
		FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String mail= Admin.getEmail();
        String password= Admin.getPassword();
        if (DatabaseOperations.checkadminlogin(mail,password) == true) {
        	return "indexAdmin.xhtml?faces-redirect=true";
        }
        else {
        	FacesContext.getCurrentInstance().addMessage( "login:password", new FacesMessage( "User not found!"));
        	return null;
        }
        
	}

}
