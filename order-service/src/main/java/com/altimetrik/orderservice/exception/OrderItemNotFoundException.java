package com.altimetrik.orderservice.exception;

public class OrderItemNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderItemNotFoundException(String msg){
		super(msg);
	}
}
