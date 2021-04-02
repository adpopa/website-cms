/**
 * ProductModelTest.java
 */
package com.ap00921.website.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;

import static org.junit.Assert.assertEquals;

/**
 * @author Alex Daniel Popa
 *
 */
public class ProductModelUnitTest {

	private static Colour c1 = null;
	private static Colour c2 = null;
	
	private static Variation v1 = null;
	private static Variation v2 = null;
	
	private static List<Colour> colours = new ArrayList<Colour>();
	private static List<Variation> variations = new ArrayList<Variation>();
	
	@BeforeClass
	public static void setupObjects(){
		
		v1 = new Variation("vOne", 43);
		v2 = new Variation("vTwo", 64);
		
		variations.add(v1);
		variations.add(v2);
		
		c1 = new Colour("Colour1", variations);
		c2 = new Colour("Colour2", variations);
		
		colours.add(c1);
		colours.add(c2);
	}
	
	@Test
	public void testAccessorsMutators() {
		Product p = new Product("Men", "Brand", "Type", "Name", 11.11, colours);
		
		assertEquals("Men", p.getProductGender());
		assertEquals("Brand", p.getProductBrand());
		assertEquals("Type", p.getProductType());
		assertEquals("Name", p.getProductName());
		assertEquals(11.11, p.getProductPrice(), 0);
		assertEquals(colours, p.getProductColours());
	}
	
}
