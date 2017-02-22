package com.shipt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Java object class for mapping Customer
 *
 */
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String emailId;
	private String firstName;
	private String lastName;
	
	List<Order> listOrders = new ArrayList<Order>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Order> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		this.listOrders = listOrders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", emailId=" + emailId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", listOrders=" + listOrders + "]";
	}

	
	
	
}
