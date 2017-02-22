package com.shipt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Java object class for mapping Product
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String productName;
	private double pricePerUnit;
	private double quantityAvailable;
	
	List<Category> listOfCategories = new ArrayList<Category>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public List<Category> getListOfCategories() {
		return listOfCategories;
	}

	public void setListOfCategories(List<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}

	public double getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(double quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", pricePerUnit=" + pricePerUnit + ", quantity="
				+ quantityAvailable + ", listOfCategories=" + listOfCategories + "]";
	}
}
