package com.project.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.project.dao.BloodStockDAO;
import com.project.entities.StockEntityManager;

@ManagedBean(name="Stock")
public class Stock {

	
	private String bloodGroup;
	private int quantite;
	private List <StockEntityManager> quantities;
	
	public List load(){ 
	    // attributes to be initialized at every refresh
		return(BloodStockDAO.getAllBloodGroupQuantite());
		
	}
	public Stock() {
		quantities = BloodStockDAO.getAllBloodGroupQuantite();
	}
	
	public Stock(String bloodGroup, int quantite) {
		super();
		this.bloodGroup = bloodGroup;
		this.quantite = quantite;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	/*public List<String> getQuantities() {
		return quantities;
	}
	public void setQuantities(List<String> quantities) {
		this.quantities = quantities;
	}*/
	public List<StockEntityManager> getQuantities() {
		return quantities;
	}
	public void setQuantities(List<StockEntityManager> quantities) {
		this.quantities = quantities;
	}

	/*public List<Stock> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Stock> quantities) {
		this.quantities = quantities;
	}
*/

	
}
