/**
 * ColourServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.service.ColourService;
import com.ap00921.website.service.VariationService;
import com.ap00921.website.service.impl.ColourServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class ColourServiceImplUnitTest {
    @TestConfiguration
    static class ColourServiceImplUnitTestContextConfiguration {
    	@Bean
        public ColourService colourService() {
            return new ColourServiceImpl();
        }
    }
	
	@Autowired
	private ColourService colourService;
	
	@MockBean
	private ColourRepository colourRepository;
	
	@MockBean
	private VariationService variationService;
	
	Colour colour = null;
	
	List<Colour> colours = new ArrayList<Colour>();
	
	private List<Variation> variations = new ArrayList<Variation>();
	
	@Before
	public void before() {
		Variation variation = new Variation("vOne", 43);
		variations.add(variation);
		
		colour = new Colour("name", variations);
		
		Mockito.when(variationService.insert(variation)).thenReturn(variation);
		Mockito.when(variationService.update(variation)).thenReturn(variation);
		
		Mockito.when(colourRepository.insert(colour)).thenReturn(colour);
		Mockito.when(colourRepository.save(colour)).thenReturn(colour);
		Mockito.when(colourRepository.findAll()).thenReturn(null);
	}

	@Test
	public void whenInsertValidColour_thenColourShouldBeReturned() {
		// when
		Colour returned = colourService.insert(colour);
		
		// then
		assertThat(returned.getColourId()).isEqualTo(colour.getColourId());
	}
	
	@Test
	public void whenUpdateValidColour_thenColourShouldBeReturned() {
		// given
		colour.setProductColour("newname");
		
		// when
		Colour returned = colourService.update(colour);
		
		//then
		assertThat(returned.getProductColour()).isEqualTo(colour.getProductColour());
	}
	
	@Test
	public void whenRemoveByListColour_thenNullShouldBeReturned() {
		// when
		colourService.removeByList(colours);
		
		// then
		assertNull(colourRepository.findAll());
	}
	
	
}
