package com.shipt.exception;

/**
 * 
 * @author rahul singh
 * @email rahpsing@iu.edu
 * 
 * Project Based exception class implemented
 * P.S : No new statements added over the Exception class
 *
 */
public class ShiptException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ShiptException(String message) {
		
		super(message);
	}
	

	public ShiptException(String message, Throwable t) {
		
		super(message,t);
	}
	
	public ShiptException(Throwable t) {
		
		super(t);
	}
}
