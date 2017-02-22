package com.shipt.util;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.shipt.exception.ShiptException;

/**
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Sample Class that shall manage inventory of items
 * After every product purchased the static map shall be updated by calling the @method subtractProductQuantity
 * Alternatively if new items are added to inventory the @method addProductQuantity should be called.
 * Methods are synchronized and concurrenthashmap is used to avoid race conditions and deadlock.
 */
public class Inventory implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private static Map<String,Double> mapOfProductIdAndQuantityAvailable = new ConcurrentHashMap<String,Double>();
	
	public static synchronized Double getProductQuantity(String productId) {
		
		return mapOfProductIdAndQuantityAvailable.get(productId);
	}
	
	public static synchronized void subtractProductQuantity(String productId, double quantityToBeRemoved) throws ShiptException {
		
		double quantity = mapOfProductIdAndQuantityAvailable.get(productId);
		quantity = quantity - quantityToBeRemoved;
		if(quantity < 0)
			throw new ShiptException("Quantity less than desired");
		
		mapOfProductIdAndQuantityAvailable.put(productId, quantity);
	}
	
	public static synchronized void addProductQuantity(String productId, double quantityToBeAdded) {
		
		double quantity = mapOfProductIdAndQuantityAvailable.get(productId);
		quantity = quantity + quantityToBeAdded;
		mapOfProductIdAndQuantityAvailable.put(productId, quantity);
	}
}
