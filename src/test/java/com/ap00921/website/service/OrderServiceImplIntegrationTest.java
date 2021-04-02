package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;
import com.ap00921.website.repository.OrderRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplIntegrationTest {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderService orderService;
	
	private Order order = null;
	
	@Before
	public void before() {
		orderRepository.deleteAll();
		
		OrderAddress address = null;
		List<OrderItem> items = new ArrayList<OrderItem>();
		
		String s = "string";
		
		address = new OrderAddress(s,s,s,s,s,s,s);
		OrderItem item = new OrderItem(s,s,s,s,s,s,s,10,10);
		
		items.add(item);
		
		order = new Order("userId", address, items, 10);
		orderRepository.save(order);
	}
	
	@After
	public void after() {
		orderRepository.deleteAll();
	}
	
	@Test
	public void whenGetAllOrders_thenListOfOrdersShouldBeReturned() {
		// when
		List<Order> returned = orderService.getAllOrders();
		
		// then
		assertThat(returned.get(0).getOrderId()).isEqualTo(order.getOrderId());
	}
	
	@Test
	public void whenInserValidOrder_thenOrderShouldBeReturned() {
		// given
		this.after();
		
		// when
		Order returned = orderService.insert(order);
		
		// then
		assertThat(returned.getOrderId()).isEqualTo(order.getOrderId());
	}
	
	@Test
	public void whenRemoveByOrderId_thenNullShouldBeReturned() {
		// when
		orderService.remove(order.getOrderId());
		
		// then
		assertNull(orderRepository.findByOrderId(order.getOrderId()));
	}
	
}
