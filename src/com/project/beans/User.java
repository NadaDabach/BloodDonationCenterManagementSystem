package com.project.beans;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.project.dao.DatabaseOperations;
import com.project.dao.UserDatabaseOperations;
import com.project.entities.UserEntityManager;

@ViewScoped
@ManagedBean(name="User")
public class User {
	private String name;
	private String email;
	private int num_tel;
	private String adresse;
	private String password;
	private String username;
	private String gender;
	private String day;
	private String month;
	private String year;
	private String city;
	private int id_maladie;
	private List<Maladies> maladies;
		
	public User() {
		super();
		maladies = DatabaseOperations.getAllMaladiesDetails();
	}

	
	
	public User(String name, String email, int num_tel, String adresse, String password, String username, String gender,
			String day, String month, String year, String city, int id_maladie) {
		super();
		this.name = name;
		this.email = email;
		this.num_tel = num_tel;
		this.adresse = adresse;
		this.password = password;
		this.username = username;
		this.gender = gender;
		this.day = day;
		this.month = month;
		this.year = year;
		this.city = city;
		this.id_maladie = id_maladie;
		maladies = DatabaseOperations.getAllMaladiesDetails();
	}


	public int getNum_tel() {
		return num_tel;
	}

	public void setNum_tel(int num_tel) {
		this.num_tel = num_tel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}	
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
		
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getId_maladie() {
		return id_maladie;
	}

	public void setId_maladie(int id_maladie) {
		this.id_maladie = id_maladie;
	}



	public List<Maladies> getMaladies() {
		return maladies;
	}



	public void setMaladies(List<Maladies> maladies) { 
		this.maladies = maladies;
	}
	
	public String RegisterUser(User User) {
		
        if (UserDatabaseOperations.isusernamePresent(User.getUsername()) == false) {
        	UserDatabaseOperations.createNewUser(User.getName(),User.getEmail(),User.getNum_tel(),User.getAdresse(),User.getPassword(),User.getUsername(),User.getGender(),User.getDay(),User.getMonth(),User.getYear(),User.getCity(),User.getId_maladie());
        	return "indexLoginUser.xhtml?faces-redirect=true";
        }
        else {
        	FacesContext.getCurrentInstance().addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username already exsist !", " Register Error!"));
        	return "indexRegister.xhtml?faces-redirect=true";
        }
        
	}
	
	public String UpdateUser() {
		HttpSession session = SessionUtils.getSession();
		UserEntityManager us = (UserEntityManager) session.getAttribute("user"); 
		UserDatabaseOperations.UpdateUser(us);
		//us.getName(),us.getEmail(),us.getNum_tel(),us.getAdresse(),us.getPassword(),us.getUsername(),us.getGender(),us.getDay(),us.getMonth(),us.getYear(),us.getCity(),us.getMaladie().getId_maladie(
        return "indexProfil.xhtml?faces-redirect=true";
	}

	public String gotoeditprofile() throws IOException {
        //FacesContext.getCurrentInstance().getExternalContext().redirect("/user/editProfile.xhtml");//add your URL here, instead of list.do
		return "editProfil.xhtml?faces-redirect=true";
	}
	
}
