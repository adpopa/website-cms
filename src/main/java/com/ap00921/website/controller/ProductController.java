/**
 * ProductController.java
 */
package com.ap00921.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap00921.website.model.product.Product;
import com.ap00921.website.service.ProductService;

/**
 * @author Alex Daniel Popa
 *
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public List<Product> getAll() {
		List<Product> products = this.productService.getAllProducts();
		return products;
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getByProductId(@PathVariable("productId") String productId) {
		Product product = this.productService.getByProductId(productId);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

}
