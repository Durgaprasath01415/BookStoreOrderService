package com.bookstore.main.dto;

import lombok.Data;

@Data
public class OrderDTO {
	private long quantity;
	private double price;
	private String address;
}
