package com.project.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class AdminEntityManager {
	
	@Id
	private int id_admin;
	private String email;
	private String password;
	
	public AdminEntityManager() {
		
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
}
