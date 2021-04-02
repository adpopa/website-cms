/**
 * VariationServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.VariationRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VariationServiceImplIntegrationTest {

	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private VariationService variationService;
	
	private Variation variation = null;
	
	@Before
	public void before() {
		variation = new Variation("vOne", 43);
		
		variationRepository.insert(variation);
	}
	
	@After
	public void after() {
		variationRepository.deleteAll();
	}
	
	@Test
	public void whenInsertValidVariation_thenVariationShouldBeReturned() {
		// given
		this.after();
		
		// when
		Variation returned = variationService.insert(variation);
		
		// then
		assertThat(returned.getVariationId()).isEqualTo(variation.getVariationId());
	}
	
	@Test
	public void whenUpdateValidVariation_thenVariationShouldBeReturned() {
		// given
		variation.setProductSize("newname");
		
		// when
		Variation returned = variationService.update(variation);
		
		//then
		assertNotNull(returned.getUpdatedAt());
	}
	
	@Test
	public void whenRemoveByProductId_thenNullShouldBeReturned() {
		// when
		variationService.remove(variation.getVariationId());
		
		// then
		assertNull(variationRepository.findByVariationId(variation.getVariationId()));
	}
	
}
