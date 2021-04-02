/**
 * ColourServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.repository.VariationRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ColourServiceImplIntegrationTest {

	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ColourService colourService;
	
	private Colour colour = null;
	
	private List<Variation> variations = new ArrayList<Variation>();
	
	@Before
	public void before() {
		variationRepository.deleteAll();
		colourRepository.deleteAll();
		
		Variation v1 = new Variation("vOne", 43);
		Variation v2 = new Variation("vTwo", 64);
		variations.add(v1);
		variations.add(v2);
		
		colour = new Colour("name", variations);

		colourRepository.insert(colour);
		variationRepository.insert(variations);
	}
	
	@After
	public void after() {
		variationRepository.deleteAll();
		colourRepository.deleteAll();
	}

	@Test
	public void whenInsertValidColour_thenColourShouldBeReturned() {
		// given
		this.after();
		
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
		// given
		List<Colour> colours = new ArrayList<Colour>();
		colours.add(colour);
		
		// when
		colourService.removeByList(colours);
		
		// then
		assertThat(colourRepository.findAll().isEmpty());;
	}
	
	
}
