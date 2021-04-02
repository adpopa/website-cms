/**
 * ColourModelTests.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
public class ColourModelUnitTest {

	private static Variation v1 = null;
	private static Variation v2 = null;
	
	private static List<Variation> variations = new ArrayList<Variation>();
	
	@BeforeClass
	public static void setupObjects(){
		
		v1 = new Variation("vOne", 43);
		v2 = new Variation("vTwo", 64);
		
		variations.add(v1);
		variations.add(v2);
	}
	
	@Test
	public void testAccessorsMutators() {
		Colour c = new Colour("Colour", variations);
		
		assertEquals("Colour", c.getProductColour());
		assertEquals(variations, c.getProductVariations());
	}
	
}
