/**
 * ProductControllerTests.java
 */
package com.ap00921.website.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.repository.ProductRepository;
import com.ap00921.website.repository.VariationRepository;


/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

	public final static String PATH = "/api/products";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	private Product product = null;
	
	@Before
	public void before() {
		productRepository.deleteAll();
		variationRepository.deleteAll();
		colourRepository.deleteAll();
		
		List<Colour> colours = new ArrayList<Colour>();
		List<Variation> variations = new ArrayList<Variation>();
		
		Variation v1 = new Variation("vOne", 43);
		variations.add(v1);
		Variation v2 = new Variation("vTwo", 64);
		variations.add(v2);
		
		Colour c1 = new Colour("Colour1", variations);
		colours.add(c1);
		Colour c2 = new Colour("Colour2", variations);
		colours.add(c2);
		
		product = new Product("Men", "Brand", "Type", "Name", 11.11, colours);
		
		productRepository.insert(product);
	}
	
	@After
	public void after() {
		productRepository.deleteAll();
		variationRepository.deleteAll();
		colourRepository.deleteAll();
	}
	
	@Test
	public void givenProducts_whenGetAllProducts_thenStatus200() throws Exception {
		
		mockMvc.perform(get(PATH + "/all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].productName", is(this.product.getProductName())));
		
	}
	
	@Test
	public void givenProduct_whenGetProductByProductId_thenStatus200() throws Exception {
		
		mockMvc.perform(get(PATH + "/" + product.getProductId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productName", is(this.product.getProductName())));
		
	}
	
}
