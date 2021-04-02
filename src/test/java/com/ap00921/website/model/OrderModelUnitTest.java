/**
 * OrderModelUnitTest.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;

/**
 * @author Alex Daniel Popa
 *
 */
public class OrderModelUnitTest {

	private Order order = null;
	
	private OrderAddress address = null;
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	@Before
	public void before() {
		String s = "string";
		
		address = new OrderAddress(s,s,s,s,s,s,s);
		OrderItem item = new OrderItem(s,s,s,s,s,s,s,10,10);
		
		items.add(item);
		
	}
	
	@Test
	public void testAccessorsMutators() {
		order = new Order("userId", address, items, 10);
		
		assertEquals(order.getAddress(), address);
		assertEquals(order.getItems(), items);
		assertEquals(order.getTotal(), 10, 0);
	}
	
	
}
