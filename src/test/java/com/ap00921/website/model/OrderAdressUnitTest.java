/**
 * OrderAdress.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ap00921.website.model.order.OrderAddress;

/**
 * @author Alex Daniel Popa
 *
 */
public class OrderAdressUnitTest {
	
	private OrderAddress address = null;
	
	private static String s = "string";
	
	@Test
	public void testAccessorsMutators() {
		address = new OrderAddress(s,s,s,s,s,s,s);

		assertEquals(address.getAdress1(), "string");
		assertEquals(address.getAdress2(), "string");
		assertEquals(address.getCity(), "string");
		assertEquals(address.getCountry(), "string");
		assertEquals(address.getName(), "string");
		assertEquals(address.getPhone(), "string");
		assertEquals(address.getPostcode(), "string");
	}
}
