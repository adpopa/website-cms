/**
 * OrderRepositoryIntegrationTest.java
 */
package com.ap00921.website.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class OrderRepositoryIntegrationTest {

	@Autowired
	private OrderRepository orderRepository;
	
	private static OrderAddress address = null;
	private static List<OrderItem> items = new ArrayList<OrderItem>();

	@Before
	public void before() {
		orderRepository.deleteAll();
	}
	
	@BeforeClass
	public static void setUp() {
		String s = "string";
		
		address = new OrderAddress(s,s,s,s,s,s,s);
		OrderItem item = new OrderItem(s,s,s,s,s,s,s,10,10);
		
		items.add(item);
	}

	@Test
	public void whenGetByOrderId_thenReturnOrder() {
		// given
		Order order = new Order("userId", address, items, 10);
		orderRepository.save(order);

		// when
		Order found = orderRepository.findByOrderId(order.getOrderId());

		// then
		assertThat(found.getOrderId()).isEqualTo(order.getOrderId());
	}

	@After
	public void after() {
		orderRepository.deleteAll();
	}

}
