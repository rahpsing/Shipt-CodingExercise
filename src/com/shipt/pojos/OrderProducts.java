package com.shipt.pojos;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Java object class for mapping relation between Order and Products in the Order
 *
 */
public class OrderProducts {

	private double quantity;
	private Order order;
	private Product product;
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "OrderProducts [quantity=" + quantity + ", order=" + order + ", product=" + product + "]";
	}
	
	
	
}
