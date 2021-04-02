/**
 * OrderService.java
 */
package com.ap00921.website.service;

import java.util.List;

import com.ap00921.website.model.order.Order;

/**
 * @author Alex Daniel Popa
 *
 */
public interface OrderService {

	public List<Order> getAllOrders();

	public Order insert(Order order);
	
	public void remove(String orderId);
	
}
