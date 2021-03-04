package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur")
public class UserEntityManager {
	
	@Id
	private String username;
	private String name;
	private String email;
	private int num_tel;
	private String adresse;
	private String password;
	//private String sexe;
	private String gender;
	private String day;
	private String month;
	private String year;
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "id_maladie")
	private MaladiesEntityManager maladie; 
		
	public UserEntityManager() {
		super();
	}

	
	public UserEntityManager(String name, String email, int num_tel, String adresse, String password, String username,
			String gender, String day, String month, String year, String city, MaladiesEntityManager maladie) {
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
		this.maladie = maladie;
	}



	/*public UserEntityManager(String name, String email, int num_tel) {
		super();
		this.name = name;
		this.email = email;
		this.num_tel = num_tel;
	}*/

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
	
	/*public String getSexe() {
		return sexe;
	}
	
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}*/
	
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


	public MaladiesEntityManager getMaladie() {
		return maladie;
	}


	public void setMaladie(MaladiesEntityManager maladie) {
		this.maladie = maladie;
	}

	

}
