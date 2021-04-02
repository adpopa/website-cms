/**
 * ProductDao.java
 */
package com.ap00921.website.loader;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.repository.ProductRepository;
import com.ap00921.website.repository.VariationRepository;
import com.ap00921.website.service.ProductService;

/**
 * @author Alex Daniel Popa
 *
 */
//@Component
public class ProductLoader implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private VariationRepository variationRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Product jacket = new Product("Men",
									 "Hugo",
									 "Jacket",
									 "Highline Jacket", 
									 100.00, 
									 Arrays.asList(
											new Colour("White", Arrays.asList( 
													new Variation("Sdas", 67),
													new Variation("Mas", 60),
													new Variation("XL", 50))
													),
											new Colour("Blue", Arrays.asList( 
													new Variation("Sdas", 43),
													new Variation("Mas", 64),
													new Variation("XL", 34))
													)
												)
				);

		Product sweater = new Product(	"Men",
										"Hugo",
										"Sweater",
										"Logo Sweater", 
										25.00, 
										Arrays.asList(
												new Colour("Red", Arrays.asList( 
														new Variation("S", 67),
														new Variation("M", 60),
														new Variation("L", 50))
												),
												new Colour("Black", Arrays.asList( 
												new Variation("S", 43),
												new Variation("M", 64),
												new Variation("L", 34))
												)
											)
		);

		Product jeans = new Product("Men",
									"Hugo",
									"Jeans",
									"550 Jeans", 
									80.99, 
									 Arrays.asList(
												new Colour("Black", Arrays.asList( 
														new Variation("S", 67),
														new Variation("M", 60),
														new Variation("L", 50))
														),
												new Colour("Yellow", Arrays.asList( 
														new Variation("S", 43),
														new Variation("M", 64),
														new Variation("L", 34))
														)
													)
						);
				
		this.productRepository.deleteAll();
		this.colourRepository.deleteAll();
		this.variationRepository.deleteAll();
		
		this.productService.insert(sweater);
		this.productService.insert(jeans);
		this.productService.insert(jacket);

	}

}
