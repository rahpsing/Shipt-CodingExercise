package com.shipt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Java object class for mapping category
 *
 */
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String categoryName;
	
	List<Product> listOfProducts = new ArrayList<Product>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", listOfProducts=" + listOfProducts + "]";
	}
	
	

}
