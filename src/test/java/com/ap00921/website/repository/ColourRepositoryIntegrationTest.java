/**
 * ColourRepositoryIntegrationTest.java
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
import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class ColourRepositoryIntegrationTest {
	
	private static List<Variation> variations = new ArrayList<Variation>();
	
	@Autowired
	private ColourRepository colourRepostory;
	
	@BeforeClass
	public static void setupObjects() {
		
		Variation v1 = new Variation("vOne", 43);
		Variation v2 = new Variation("vTwo", 64);
		
		variations.add(v1);
		variations.add(v2);
	}
	
	@Before
	public void before() {
		colourRepostory.deleteAll();
	}

	
	@Test
	public void whenFindByColourId_thenReturnColour() {
		
		// given
		Colour colour = new Colour("Colour1", variations);
		colourRepostory.save(colour);
		
		// when
		Colour found = colourRepostory.findByColourId(colour.getColourId());
		
		// then
		assertThat(found.getColourId()).isEqualTo(colour.getColourId());
	}
	
	
	@After
	public void after() {
		colourRepostory.deleteAll();
	}
}
