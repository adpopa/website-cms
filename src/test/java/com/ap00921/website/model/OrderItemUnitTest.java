/**
 * OrderItem.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ap00921.website.model.order.OrderItem;

/**
 * @author Alex Daniel Popa
 *
 */
public class OrderItemUnitTest {

	private OrderItem item = null;
	
	private static String s = "string";
	
	@Test
	public void testAccessorsMutators() {
		item = new OrderItem(s,s,s,s,s,s,s,10,10);

		assertEquals(item.getProductId(), "string");
		assertEquals(item.getVariationId(), "string");
		assertEquals(item.getColourId(), "string");
		assertEquals(item.getItemBrand(), "string");
		assertEquals(item.getItemName(), "string");
		assertEquals(item.getItemSize(), "string");
		assertEquals(item.getItemPrice(), 10, 0);
		assertEquals(item.getItemQuantity(), 10, 0);
	}
	
}
