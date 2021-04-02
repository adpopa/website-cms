/**
 * ProductServiceImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap00921.website.exceptions.product.ProductIdException;
import com.ap00921.website.exceptions.product.ProductNameException;
import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.repository.ProductRepository;
import com.ap00921.website.service.ColourService;
import com.ap00921.website.service.ProductService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ColourService colourService;

	@Override
	public List<Product> getAllProducts() {
		List<Product> items = this.productRepository.findAll();
		
		return items;
	}

	@Override
	public Product insert(Product product) {
		try {
			product.setProductName(WordUtils.capitalizeFully(product.getProductName()));
			product.setCreatedAt(new Date());

			List<Colour> newColours = new ArrayList<Colour>();
			for(Colour c : product.getProductColours())
				newColours.add(this.colourService.insert(c));
			
			return this.productRepository.insert(product);
		} catch (Exception e) {
			throw new ProductNameException("Product name '" + product.getProductName() + "' already exists");
		}
	}

	@Override
	public Product update(Product product) {
		Product p = this.productRepository.findByProductId(product.getProductId());

		if (p == null) {
			throw new ProductIdException("The product with id '" + product.getProductId()
					+ "' it cannot be updated because it does not exist");
		}

		if (product.getCreatedAt() != p.getCreatedAt()) {
			product.setCreatedAt(p.getCreatedAt());
		}

		List<Colour> newColours = new ArrayList<Colour>();
		for (Colour c : product.getProductColours()) {
			newColours.add(this.colourService.update(c));
		}
		
		for (Colour col : product.getProductColours())
			p.getProductColours().removeIf(c -> c.getColourId().equals(col.getColourId()));
		
		this.colourService.removeByList(p.getProductColours());
		
		product.setUpdatedAt(new Date());
		return this.productRepository.save(product);
		
	}

	@Override
	public void remove(String productId) {
		Product product = this.productRepository.findByProductId(productId);

		if (product == null) {
			throw new ProductIdException("The product with id '" + productId + "' does not exist");
		}

		this.colourService.removeByList(product.getProductColours());

		this.productRepository.deleteById(productId);
	}

	@Override
	public Product getByProductId(String productId) {
		Product product = this.productRepository.findByProductId(productId);

		if (product == null) {
			throw new ProductIdException("The product with id '" + productId + "' does not exist");
		}

		return product;
	}

}
