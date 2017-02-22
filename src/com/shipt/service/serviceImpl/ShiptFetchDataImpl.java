package com.shipt.service.serviceImpl;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.shipt.pojos.Customer;
import com.shipt.pojos.Order;
import com.shipt.service.serviceAPI.ShiptFetchDataAPI;
import com.shipt.util.HibernateUtil;
import com.shipt.util.ShiptConstants;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Implementation class for fetch data services
 * Answers to question 4,5 and 6 from the document
 *
 */
@RunWith(JUnit4.class)
public class ShiptFetchDataImpl implements ShiptFetchDataAPI{

	/**
	 * @author rahul singh
	 * @email rahpsing@iu.edu
	 * 
	 * Method that returns data according to following format
	 * customer_id | customer_first_name | category_id | category_name | number_purchased
	 * 
	 * Answer to Question 4
	 * 
	 */
	@Test
	@Override
	public void getAllCustomerDataAccordingToCategoryAndNumberPurchased() {
		// TODO Auto-generated method stub
	
		Session objSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = objSession.beginTransaction();
		StringBuilder objOutputBuilder = new StringBuilder();
		StringBuilder objBuilder = new StringBuilder();
		objBuilder.append("select cust.id as customer_id, cust.first_name as customer_first_name, "
				+ "pc.CATEGORY_ID as category_id,sc.category_name, sum(op.quantity) as number_purchased")
		.append(" from Shipt_Customer cust join Shipt_Order SOR on cust.id = SOR.customer_id")
		.append(" join  Order_Products op on SOR.id = op.order_id")
		.append(" join Product_Category pc on pc.product_id  = op.product_id")
		.append(" join Shipt_Category sc on pc.CATEGORY_ID = sc.ID")
		.append(" group by cust.id, cust.first_name, pc.CATEGORY_ID, sc.category_name");

		SQLQuery objQuery = objSession.createSQLQuery(objBuilder.toString());
		
		List<Object> resultList = objQuery.list();
		
		objOutputBuilder.append("Customer_ID |" + "\t\t" + "Customer_First_Name |" + "\t\t" + "Category_id |" +"\t\t" + "Category_Name |" +  "\t\t" + "Number_purchased |").append("\n");
		Iterator objIterator = resultList.iterator();
		while(objIterator.hasNext()) {
			
			Object[] tuple = (Object[]) objIterator.next();
			 String customerID = tuple[0].toString();
			 String customerFirstName = tuple[1].toString();
			 String categoryId = tuple[2].toString();
			 String categoryName = tuple[3].toString();
			 String numberPurchased = tuple[4].toString();
			 objOutputBuilder.append(customerID + " \t\t" + customerFirstName + " \t\t" + categoryId + " \t\t" + categoryName + " \t\t" + numberPurchased);
			 objOutputBuilder.append("\n");
		}
		System.out.println(objOutputBuilder.toString());
		assertNotNull(resultList); // used only for the junit test
		tx.commit();
		objSession.close();
	}
	
	/**
	 * @author rahul singh
	 * @email rahpsing@iu.edu
	 * 
	 * Method that returns the Order details for a customer
	 * @param customerId database Id of the customer whose details are desired
	 * 
	 * Answer to Question 6
	 * 
	 */
	@Override
	public void getOrdersForACustomer(int customerId) {
		// TODO Auto-generated method stub
	
		Session objSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = objSession.beginTransaction();
		
		// here I need not fetch the orders separately as I have enabled lazy loading
		// by specifying lazy = true in Customer.hbm.xml file. Thus, according to the association of order object with a customer,
		// fetch query shall be executed implicitly for the orders.
		Query objQuery = objSession.createQuery("from Customer where id = ?");
		//equivalent to select * from SHIPT_ORDER where CUSTOMER_ID=1;
																			   
		objQuery.setParameter(0, customerId);
		
		StringBuilder objBuilder = new StringBuilder();
		
		List<Customer> resultList = objQuery.list();
		objBuilder.append("Order Date" + "\t\t" + "Order Total" + "\t\t" + "Order Status").append("\n");
		
		for(Order objOrder : resultList.get(0).getListOrders()) {
			
			objBuilder.append(objOrder.getOrderDate() + "\t\t" + objOrder.getOrderTotal() + "\t\t" + objOrder.getStatus());
			objBuilder.append("\n");
			
		}
		System.out.println(objBuilder);
		tx.commit();
		objSession.close();
	}
	
	
	/**
	 * @author rahul singh
	 * @email rahpsing@iu.edu
	 * 
	 * Method that provides products sold between a period, broken by day,month or week as desired
	 * @param startDate starting Date Of period in String 'DD-MMM-YY' format
	 * @param endDate   end Date Of period in String 'DD-MMM-YY' format
	 * @param period    quantifier by which data shall be extracted accepted values(DAY,MONTH,WEEK)
	 * 
	 * Answer to Question 6
	 */
	@Override
	public void getDataAccordingToRangeAndTimeDesired(String startDate, String endDate, String period) {
		
		Session objSession = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = objSession.beginTransaction();
		StringBuilder objBuilder = new StringBuilder();
		
		if(ShiptConstants.BY_DAY == period || ShiptConstants.BY_MONTH == period) {
			
			objBuilder = getQueryForDataByMonthOrDay(startDate,endDate,period);
			
		} else if(ShiptConstants.BY_WEEK == period) {
			
			objBuilder = getQueryForDataByWeek(startDate,endDate);
		}

		SQLQuery objQuery = objSession.createSQLQuery(objBuilder.toString());
		List<Object> resultList = objQuery.list();
		Iterator iterator = resultList.iterator();
		StringBuilder objOutputBuilder = new StringBuilder(); 
		objOutputBuilder.append(period + "\t\t" + "Product" + "\t\t" + "Quantity").append("\n");
		
		while(iterator.hasNext()) {
			
			Object[] tuple = (Object[]) iterator.next();
			 String periodRequested = tuple[0].toString();
			 String productName = tuple[1].toString();
			 String quantity = tuple[2].toString();
			 objOutputBuilder.append(periodRequested + "\t\t" + productName + "\t\t" + quantity);
			 objOutputBuilder.append("\n");
		}
		
		System.out.println(objOutputBuilder.toString());
		tx.commit();
		objSession.close();
	}


	private StringBuilder getQueryForDataByWeek(String startDate, String endDate) {
		// TODO Auto-generated method stub
		StringBuilder objQueryBuilder = new StringBuilder();
		
		objQueryBuilder.append("select to_number(to_char( so.ORDER_DATE,'WW')),sp.PRODUCT_NAME,op.QUANTITY from SHIPT_ORDER so"
				+ " join ORDER_PRODUCTS op on so.id = op.ORDER_ID"
				+ " join SHIPT_PRODUCT sp on op.PRODUCT_ID = sp.ID"
				+ " where so.ORDER_DATE between '"+ startDate+"' and '"+ endDate + "'"
				+ " group by to_number(to_char( so.ORDER_DATE,'WW')), sp.PRODUCT_NAME, op.QUANTITY"
				+ "order by  to_number(to_char( so.ORDER_DATE,'WW'))");
		
		return objQueryBuilder;
	}


	private StringBuilder getQueryForDataByMonthOrDay(String startDate, String endDate, String period) {
		// TODO Auto-generated method stub
		StringBuilder objQueryBuilder = new StringBuilder();
		
		objQueryBuilder.append("select EXTRACT("+ period +" from so.ORDER_DATE),sp.PRODUCT_NAME,op.QUANTITY from SHIPT_ORDER so "
				+ "join ORDER_PRODUCTS op on so.id = op.ORDER_ID" 
				+ " join SHIPT_PRODUCT sp on op.PRODUCT_ID = sp.ID"
				+ " where so.ORDER_DATE between '"+ startDate+"' and '"+ endDate + "'"
				+ " group by EXTRACT("+ period +" from so.ORDER_DATE), sp.PRODUCT_NAME, op.QUANTITY"
		        + " order by  EXTRACT("+ period +" from so.ORDER_DATE)");
	
		
		return objQueryBuilder;
	}


}
