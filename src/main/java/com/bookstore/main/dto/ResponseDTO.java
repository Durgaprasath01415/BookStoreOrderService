package com.bookstore.main.dto;

import lombok.Data;

@Data
public class ResponseDTO {

	private String message;
	private Object data;
	
	public ResponseDTO() {
		
	}
	
	public ResponseDTO(String message) {
		this.message = message;
	}
	
	public ResponseDTO(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	
	
}
