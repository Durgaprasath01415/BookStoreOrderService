package com.bookstore.main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Orders")
@Data
public class OrderModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private LocalDate orderDate;
	private long quantity;
	private double price;
	private String address;
	private int userId;
	private int bookId;
	
}
