/**
 * VariationModelTests.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
public class VariationModelUnitTest {

	@Test
	public void testAccessorsMutators() {
		Variation v = new Variation("vTwo", 64);

		assertEquals("vTwo", v.getProductSize());
		assertEquals(64, v.getProductQuantity(), 0);
	}
	
}
