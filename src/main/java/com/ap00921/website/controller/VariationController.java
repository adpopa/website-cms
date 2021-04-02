/**
 * VariationController.java
 */
package com.ap00921.website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap00921.website.model.product.Variation;
import com.ap00921.website.service.ValidationMapErrorService;
import com.ap00921.website.service.VariationService;

/**
 * @author Alex Daniel Popa
 *
 */
@RestController
@RequestMapping("/api/variations")
@CrossOrigin
public class VariationController {
	
	@Autowired
	private VariationService variationService;

	@Autowired
	private ValidationMapErrorService validationMapErrorService;
	
	@PutMapping
	public ResponseEntity<?> updateVariation(@Valid @RequestBody Variation variation, BindingResult result) {

		ResponseEntity<?> error = validationMapErrorService.ValidationMapService(result);
		if (error != null)
			return error;

		Variation newVariation = variationService.update(variation);

		return new ResponseEntity<Variation>(newVariation, HttpStatus.OK);
	}
	
	@PutMapping("/{variationId}/sold/{quantity}")
	public ResponseEntity<?> updateStock(@PathVariable("variationId") String variationId, @PathVariable("quantity") Integer quantity) {
		this.variationService.updateStockWithQuantity(variationId, quantity);
		return new ResponseEntity<String>("Stock updated", HttpStatus.OK);
	}
	

}
