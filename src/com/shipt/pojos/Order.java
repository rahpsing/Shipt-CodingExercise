package com.shipt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shipt.util.OrderStatus;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Java object class for mapping Order
 *
 */
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	
	private Date orderDate;
	private double orderTotal;
	private OrderStatus status;
	private Customer objCustomer;
	private List<OrderProducts> listOrderProducts = new ArrayList<OrderProducts>();
	
	
	public List<OrderProducts> getListOrderProducts() {
		return listOrderProducts;
	}
	public void setListOrderProducts(List<OrderProducts> listOrderProducts) {
		this.listOrderProducts = listOrderProducts;
	}
	public Customer getObjCustomer() {
		return objCustomer;
	}
	public void setObjCustomer(Customer objCustomer) {
		this.objCustomer = objCustomer;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", orderTotal=" + orderTotal + ", status=" + status
				+ "]";
	}
	
	
}
