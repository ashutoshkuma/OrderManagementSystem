package com.altimetrik.orderservice.exception;

public class OrderItemNotAvailableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderItemNotAvailableException(String msg){
		super(msg);
	}
}
