package com.ap00921.website.service;

import java.util.List;

import com.ap00921.website.model.product.Product;

/**
 * @author Alex Daniel Popa
 *
 */
public interface ProductService {
	
	public List<Product> getAllProducts();

	public Product insert(Product product);

	public Product update(Product product);

	public void remove(String productId);
	
	public Product getByProductId(String productId);

}
