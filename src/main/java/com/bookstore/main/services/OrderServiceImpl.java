package com.bookstore.main.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookstore.main.dto.OrderDTO;
import com.bookstore.main.dto.ResponseDTO;
import com.bookstore.main.exceptions.OrderException;
import com.bookstore.main.model.OrderModel;
import com.bookstore.main.repository.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public ResponseDTO placeOrder(String token, OrderDTO orderDto,int bookId) {
		boolean verify = restTemplate.getForObject("http://bookstoreuser-client/verifyemail/" + token,
				Boolean.class);
		
		if (verify) {
			boolean verifyBookId = restTemplate.getForObject("http://bookstore-client/verifyBookId/" + token +"/" +bookId,
					Boolean.class);
			if (verifyBookId) {
				OrderModel orderDetails = modelMapper.map(orderDto, OrderModel.class);
				//orderDetails.setUserId(null);
				orderDetails.setBookId(bookId);
				orderDetails.setOrderDate(LocalDate.now());
				orderRepository.save(orderDetails);
				return new ResponseDTO("Order is successfully placed",orderDetails);
			}else {
				throw new OrderException(400,"Book id your placing is incorrect");
			}
		}else {
			throw new OrderException(400,"User is not present");
		}
	}

	@Override
	public ResponseDTO getAllOrder(String token) {
		boolean verify = restTemplate.getForObject("http://bookstoreuser-client/verifyemail/" + token,
				Boolean.class);
		if (verify) {
			List<OrderModel> orderList = orderRepository.findAll();
			return new ResponseDTO("List of all orders ",orderList);
		}else {
			throw new OrderException(400, "User not logged in");
		}
	}

	@Override
	public ResponseDTO getOrder(String token, int id) {
		boolean verify = restTemplate.getForObject("http://bookstoreuser-client/verifyemail/" + token,
				Boolean.class);
		if (verify) {
			List<OrderModel> orderListOfAUser = orderRepository.findAllByUserId(id);
			return new ResponseDTO("User of id:" + id, orderListOfAUser);
		}else {
			throw new OrderException(400, "User not logged in");
		}
	}

	@Override
	public ResponseDTO cancelorder(String token, int orderId) {
		boolean verify = restTemplate.getForObject("http://bookstoreuser-client/verifyemail/" + token,
				Boolean.class);
		if (verify) {
			orderRepository.deleteById(orderId);
			return new ResponseDTO("Order is cancelled","Id :" +orderId);
		}else {
			throw new OrderException(400, "User not logged in");
		}
	}
	
}
