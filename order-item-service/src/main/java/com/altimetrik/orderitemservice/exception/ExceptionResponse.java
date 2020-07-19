package com.altimetrik.orderitemservice.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {	

	public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
	
	
}
