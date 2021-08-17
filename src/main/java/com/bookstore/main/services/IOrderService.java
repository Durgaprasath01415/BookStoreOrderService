package com.bookstore.main.services;

import org.springframework.stereotype.Service;

import com.bookstore.main.dto.OrderDTO;
import com.bookstore.main.dto.ResponseDTO;

@Service
public interface IOrderService {

	ResponseDTO placeOrder(String token, OrderDTO orderDto,int bookId);

	ResponseDTO getAllOrder(String token);

	ResponseDTO getOrder(String token, int id);

	ResponseDTO cancelorder(String token, int orderId);

}
