package com.bookstore.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.main.dto.OrderDTO;
import com.bookstore.main.dto.ResponseDTO;
import com.bookstore.main.services.IOrderService;

@RestController
public class OrderController {
	@Autowired
	IOrderService orderService;

	@PostMapping("/placeorder/{token}/{bookId}")
	public ResponseEntity<ResponseDTO> placeOrder(@PathVariable String token,@RequestBody OrderDTO orderDto,@RequestParam int bookId){
		ResponseDTO respDTO = orderService.placeOrder(token,orderDto,bookId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getallorder/{token}")
	public ResponseEntity<ResponseDTO> getAllOrder(@PathVariable String token){
		ResponseDTO respDTO = orderService.getAllOrder(token);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@GetMapping("/getorder/{token}/{id}")
	public ResponseEntity<ResponseDTO> getOrder(@PathVariable String token,@PathVariable int id){
		ResponseDTO respDTO = orderService.getOrder(token,id);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
	
	@DeleteMapping("/cancelorder/{token}/{orderId}")
	public ResponseEntity<ResponseDTO> cancelorder(@PathVariable String token,@PathVariable int orderId){
		ResponseDTO respDTO = orderService.cancelorder(token,orderId);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}
}
