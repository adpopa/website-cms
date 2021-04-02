/**
 * OrderRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ap00921.website.model.order.Order;

/**
 * @author Alex Daniel Popa
 *
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String>{

	public Order findByOrderId(String orderId);
	
}
