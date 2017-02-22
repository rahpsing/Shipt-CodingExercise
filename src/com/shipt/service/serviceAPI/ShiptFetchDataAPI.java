package com.shipt.service.serviceAPI;

import com.shipt.service.serviceImpl.ShiptFetchDataImpl;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 *
 * API for fetching data. Answers to question 4,5,6 from question set.
 * See {@link ShiptFetchDataImpl} for implementation of these methods.
 */
		
public interface ShiptFetchDataAPI {

	public void getAllCustomerDataAccordingToCategoryAndNumberPurchased(); // Question 4

	public void getDataAccordingToRangeAndTimeDesired(String startDate, String endDate, String period); // Question 5

	public void getOrdersForACustomer(int customerId); //Question 6
}
