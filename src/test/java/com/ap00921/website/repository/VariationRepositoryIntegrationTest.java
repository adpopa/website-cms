/**
 * VariationRepositoryIntegrationTest.java
 */
package com.ap00921.website.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class VariationRepositoryIntegrationTest {

	@Autowired
	private VariationRepository variationRepository;
	
	
	@Before
	public void before() {
		variationRepository.deleteAll();
	}
	
	@Test
	public void whenFindByVariation_thenReturnVariation() {
		// given
		Variation variation = new Variation("vOne", 10);
		variationRepository.save(variation);
		
		// when
		Variation found = variationRepository.findByVariationId(variation.getVariationId());
		
		// then
		assertThat(found.getVariationId()).isEqualTo(variation.getVariationId());
	}
	
	@After
	public void after() {
		variationRepository.deleteAll();
	}
	
}
