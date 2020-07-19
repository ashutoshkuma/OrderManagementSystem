package com.altimetrik.orderitemservice.exception;

public class OrderItemNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderItemNotFoundException(String msg){
		super(msg);
	}
}
