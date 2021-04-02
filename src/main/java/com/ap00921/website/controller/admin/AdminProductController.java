/**
 * DashboardProductController.java
 */
package com.ap00921.website.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap00921.website.model.product.Product;
import com.ap00921.website.service.ProductService;
import com.ap00921.website.service.ValidationMapErrorService;

/**
 * @author Alex Daniel Popa
 *
 */
@RestController
@RequestMapping("/api/admin/products")
@CrossOrigin
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ValidationMapErrorService validationMapErrorService;

	@PostMapping
	public ResponseEntity<?> insertProduct(@Valid @RequestBody Product product, BindingResult result) {

		ResponseEntity<?> error = validationMapErrorService.ProductValidationMapService(result);
		if (error != null)
			return error;

		Product newProduct = productService.insert(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result) {

		ResponseEntity<?> error = validationMapErrorService.ProductValidationMapService(result);
		if (error != null)
			return error;

		Product newProduct = productService.update(product);

		return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<?> removeProduct(@PathVariable("productId") String productId) {
		this.productService.remove(productId);
		return new ResponseEntity<String>("Product with id:'" + productId + "' removed", HttpStatus.OK);
	}

}
