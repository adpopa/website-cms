/**
 * OrderServiceImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;
import com.ap00921.website.repository.OrderRepository;
import com.ap00921.website.security.CryptoUtils;
import com.ap00921.website.service.OrderService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CryptoUtils cryptoUtils;

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders = this.orderRepository.findAll();
		
		for(Order order: orders) {
			OrderAddress address = new OrderAddress();
			address.setAdress1(cryptoUtils.decryptor(order.getAddress().getAdress1()));
			address.setAdress2(cryptoUtils.decryptor(order.getAddress().getAdress2()));
			address.setCity(cryptoUtils.decryptor(order.getAddress().getCity()));
			address.setCountry(cryptoUtils.decryptor(order.getAddress().getCountry()));
			address.setName(cryptoUtils.decryptor(order.getAddress().getName()));
			address.setPhone(cryptoUtils.decryptor(order.getAddress().getPhone()));
			address.setPostcode(cryptoUtils.decryptor(order.getAddress().getPostcode()));
			
			order.setAddress(address);
		}
		
		return orders;
	}

	@Override
	public Order insert(Order order) {
		order.setCreatedAt(new Date());
		double total = 0;
		
		OrderAddress address = new OrderAddress();
		address.setAdress1(cryptoUtils.encryptor(order.getAddress().getAdress1()));
		address.setAdress2(cryptoUtils.encryptor(order.getAddress().getAdress2()));
		address.setCity(cryptoUtils.encryptor(order.getAddress().getCity()));
		address.setCountry(cryptoUtils.encryptor(order.getAddress().getCountry()));
		address.setName(cryptoUtils.encryptor(order.getAddress().getName()));
		address.setPhone(cryptoUtils.encryptor(order.getAddress().getPhone()));
		address.setPostcode(cryptoUtils.encryptor(order.getAddress().getPostcode()));
		
		order.setAddress(address);
		
		for(OrderItem item : order.getItems())
			total += item.getItemPrice() * item.getItemQuantity();
		
		order.setTotal(total);
		
		return this.orderRepository.insert(order);
	}

	@Override
	public void remove(String orderId) {
		Order order = this.orderRepository.findByOrderId(orderId);
		
//		if(order == null)
//			throw new OrderIdException("The product with id '" + productId + "' does not exist");
		
		this.orderRepository.deleteById(orderId);
	}
	
	

}
