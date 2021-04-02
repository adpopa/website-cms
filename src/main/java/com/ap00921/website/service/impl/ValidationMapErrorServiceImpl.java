/**
 * ValidationMapErrorServiceImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.ap00921.website.service.ValidationMapErrorService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class ValidationMapErrorServiceImpl implements ValidationMapErrorService {

	@Override
	public ResponseEntity<?> ValidationMapService(BindingResult result) {
		
		if (result.hasErrors()) {
			Map<String, String> error = new HashMap<>();
			
			result.getFieldErrors().forEach(e -> error.put(e.getField(), e.getDefaultMessage()));
			
			//Sorting the error map
			Map<String, String> errorSorted = new TreeMap<String, String>(error);

			return new ResponseEntity<Map<String, String>>(errorSorted, HttpStatus.BAD_REQUEST);
		} 
		
		return null;
	}
	
	@Override
	public ResponseEntity<?> ProductValidationMapService(BindingResult result) {
		
		//TODO: add empty list of variations error
		
		if (result.hasErrors()) {
			Map<String, Object> error = new HashMap<>();
			
			Map<String, Object> colourError = new HashMap<>(); // "productColours"
			
			Map<String, String> colourColourError = new HashMap<>(); // "productColour"
			Map<String, Object> colourVariationError = new HashMap<>(); // "productVariations"
			
			Map<String, String> colourVariationSizeError = new HashMap<>(); // "productSize"
			Map<String, String> colourVariationQuantityError = new HashMap<>(); // "productQuantity"

			for (FieldError e : result.getFieldErrors()) {
				if (e.getField().contains("productColours")) {
					String field = e.getField();
					field = field.replaceAll("[^-?0-9]+", "");
					
					if(e.getField().contains(".productColour")) {
						colourColourError.put(field, e.getDefaultMessage());
						
					} else if(e.getField().contains(".productVariations")) {
							if(e.getField().contains("productSize")) {
								colourVariationSizeError.put(field, e.getDefaultMessage());
						
							} else if(e.getField().contains("productQuantity")) {
								colourVariationQuantityError.put(field, e.getDefaultMessage());
						}
					}
				} else {
					error.put(e.getField(), e.getDefaultMessage());
				}
			}
			
			colourVariationError.put("productSize", colourVariationSizeError);
			colourVariationError.put("productQuantity", colourVariationQuantityError);
			
			colourError.put("productColour", colourColourError);
			colourError.put("productVariations", colourVariationError);
			
			error.put("productColours", colourError);
			

//			TODO: Sorted error map if needed
			Map<String, Object> errorSorted = new TreeMap<String, Object>(error);

			return new ResponseEntity<Map<String, Object>>(errorSorted, HttpStatus.BAD_REQUEST);
		}

		return null;
	}

}
