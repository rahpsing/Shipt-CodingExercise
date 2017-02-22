package com.shipt.util;

import java.util.Date;

import org.hibernate.Session;

import com.shipt.pojos.Category;
import com.shipt.pojos.Customer;
import com.shipt.pojos.Order;
import com.shipt.pojos.OrderProducts;
import com.shipt.pojos.Product;
import com.shipt.service.serviceAPI.ShiptFetchDataAPI;
import com.shipt.service.serviceImpl.ShiptFetchDataImpl;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * The application test class that shall load the session factory object and 
 * insert dummy data in the application.
 * Once done, it also calls fetch data method from the service API's after waiting on the main thread for 10 seconds
 */
public class TestClass {

		public static void main(String[] args) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();

			session.beginTransaction();
			
			Customer customer = new Customer();
			customer.setEmailId("rahul_singh919@yahoo.com");
			customer.setFirstName("Rahul");
			customer.setLastName("Singh");
			
			Category category = new Category();
			category.setCategoryName("Fruits");
			
			
			Category category2 = new Category();
			category2.setCategoryName("Electronics");
			
			
			Product product = new Product();
			product.setProductName("Light Bulbs");
			product.setPricePerUnit(5);
			product.setQuantityAvailable(50);
			
			Product product2 = new Product();
			product2.setProductName("Bananas");
			product2.setPricePerUnit(0.99);
			product2.setQuantityAvailable(100);
			
			product.getListOfCategories().add(category2);
			product2.getListOfCategories().add(category);
			
			category.getListOfProducts().add(product2);
			category2.getListOfProducts().add(product);
			
			Order order = new Order();
			order.setObjCustomer(customer);
			order.setStatus(OrderStatus.ON_ITS_WAY);
			order.setOrderDate(new Date());
			
			
			OrderProducts orderProducts = new OrderProducts();
			orderProducts.setOrder(order);
			orderProducts.setProduct(product2);
			orderProducts.setQuantity(16);
			
			OrderProducts orderProducts2 = new OrderProducts();
			orderProducts2.setOrder(order);
			orderProducts2.setProduct(product);
			orderProducts2.setQuantity(2);
			
			order.getListOrderProducts().add(orderProducts);
			order.getListOrderProducts().add(orderProducts2);
			customer.getListOrders().add(order);
			
			double orderTotal = 0.0;
			
			for(OrderProducts orderProduct : order.getListOrderProducts()) {
				orderTotal += orderProduct.getProduct().getPricePerUnit() * orderProduct.getQuantity();
			}
			
			order.setOrderTotal(orderTotal);

			session.saveOrUpdate(category);
			session.saveOrUpdate(category2);
			session.saveOrUpdate(product);
			session.saveOrUpdate(product2);
			session.saveOrUpdate(order);
			session.saveOrUpdate(customer);
			session.getTransaction().commit();
			session.close();
			
			//******** Run this code only once data has been inserted ****************/
		
			try {
				Thread.sleep(10000); // waiting for operations to be successful before running fetch code
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ShiptFetchDataAPI fetchData = new ShiptFetchDataImpl();
			fetchData.getDataAccordingToRangeAndTimeDesired("22-FEB-17", "23-FEB-17", ShiptConstants.BY_DAY);
			fetchData.getOrdersForACustomer(1);
			fetchData.getAllCustomerDataAccordingToCategoryAndNumberPurchased();
	   }

}
