package com.bookstore.main.exceptions;

import lombok.Data;

@Data
public class OrderException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int statusCode;
	private String statusMessage;
	
	public OrderException() {
		
	}
	
	public OrderException(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
}
