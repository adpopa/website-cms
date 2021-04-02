/**
 * ProductServiceTest.java
 */
package com.ap00921.website.service.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ProductRepository;
import com.ap00921.website.service.ColourService;
import com.ap00921.website.service.ProductService;
import com.ap00921.website.service.impl.ProductServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class ProductServiceImplUnitTest {
	
    @TestConfiguration
    static class ColourServiceImplUnitTestContextConfiguration {
    	@Bean
        public ProductService productService() {
            return new ProductServiceImpl();
        }
    }
    
    @MockBean
    private ProductRepository productRepository;

    @MockBean
	private ColourService colourService;
    
	@Autowired
	private ProductService productService;
	
	private Product product = null;
	
	@Before
	public void before() {
		List<Variation> variations = new ArrayList<Variation>();
		Variation variation = new Variation("vOne", 43);
		variations.add(variation);
		
		List<Colour> colours = new ArrayList<Colour>();
		Colour colour = new Colour("Colour1", variations);
		colours.add(colour);
		
		product = new Product("Men", "Brand", "Type", "Name", 11.11, colours);
		
		Mockito.when(colourService.insert(colour)).thenReturn(colour);
		Mockito.when(productRepository.insert(product)).thenReturn(product);
		
		Mockito.when(productRepository.findByProductId(product.getProductId())).thenReturn(product);
		Mockito.when(colourService.update(colour)).thenReturn(colour);
		Mockito.when(productRepository.save(product)).thenReturn(product);
		
		Mockito.when(productRepository.findAll()).thenReturn(null);
	}
	
	@Test
	public void whenInsertValidProduct_thenProductShouldBeReturned() {
		// when
		Product returned = productService.insert(product);
		
		// then
		assertThat(returned.getProductId()).isEqualTo(product.getProductId());
	}

	@Test
	public void whenUpdateValidProduct_thenProductShouldBeReturned() {
		// given
		product.setProductName("newname");
		
		// when
		Product returned = productService.update(product);
		
		//then
		assertNotNull(returned.getProductId());
	}
	
	@Test
	public void whenGetByProductId_thenProductShouldBeReturned() {
		// when
		Product returned = productService.getByProductId(product.getProductId());
		
		// then
		assertThat(returned.getProductId()).isEqualTo(product.getProductId());
	}
	
//	@Test (expected = ProductIdException.class)
	@Test
	public void whenRemoveByProductId_thenNullShouldBeReturned() {
		// when
		productService.remove(product.getProductId());
		
		// then
		assertNull(productRepository.findAll());
		
	}
	
}
