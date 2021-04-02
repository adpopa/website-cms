/**
 * VariationServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.VariationRepository;
import com.ap00921.website.service.VariationService;
import com.ap00921.website.service.impl.VariationServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class VariationServiceImplUnitTest {

    @TestConfiguration
    static class VariationServiceImplUnitTestTestConfiguration {
        @Bean
        public VariationService variationServiceService() {
            return new VariationServiceImpl();
        }
    }
	
	@MockBean
	private VariationRepository variationRepository;
	
	@Autowired
	private VariationService variationService;
	
	private Variation variation = null;
	
	@Before
	public void before() {
		variation = new Variation("vOne", 43);
		
		
		Mockito.when(variationRepository.insert(variation)).thenReturn(variation);
		Mockito.when(variationRepository.findByVariationId(variation.getVariationId())).thenReturn(variation);
		Mockito.when(variationRepository.save(variation)).thenReturn(variation);
		Mockito.when(variationRepository.findAll()).thenReturn(null);
	}
	
	@Test
	public void whenInsertValidVariation_thenVariationShouldBeReturned() {
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
		assertNotNull(returned.getVariationId());
	}
	
	@Test
	public void whenRemoveByProductId_thenNullShouldBeReturned() {
		// when
		variationService.remove(variation.getVariationId());
		
		// then
		assertNull(variationRepository.findAll());
	}
	
}
