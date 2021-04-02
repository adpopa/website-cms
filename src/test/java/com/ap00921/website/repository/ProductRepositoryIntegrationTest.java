/**
 * ProductRepositoryIntegrationTest.java
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

import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ProductRepositoryIntegrationTest {

	private static List<Colour> colours = new ArrayList<Colour>();
	private static List<Variation> variations = new ArrayList<Variation>();
	
	@Autowired
	private ProductRepository productRepository;
	
	@BeforeClass
	public static void setupObjects() {
		
		Variation v1 = new Variation("vOne", 43);
		Variation v2 = new Variation("vTwo", 64);
		
		variations.add(v1);
		variations.add(v2);
		
		Colour c1 = new Colour("Colour1", variations);
		Colour c2 = new Colour("Colour2", variations);
		
		colours.add(c1);
		colours.add(c2);
	}
	
	@Before
	public void before() {
		productRepository.deleteAll();
	}
	
	
	@Test
	public void whenFindByProductId_thenReturnProduct() {
		// given
		Product product = new Product("Men", "Brand", "Type", "Name", 11.11, colours);
		productRepository.save(product);
		
		// when
		Product found = productRepository.findByProductId(product.getProductId());
		
		// then
		assertThat(found.getProductId()).isEqualTo(product.getProductId());
	}
	
	
	@After
	public void after() {
		productRepository.deleteAll();
	}
}
