/**
 * ProductValidationMapErrorService.java
 */
package com.ap00921.website.service;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * @author Alex Daniel Popa
 *
 */
public interface ValidationMapErrorService {

	public ResponseEntity<?> ValidationMapService(BindingResult result);

	public ResponseEntity<?> ProductValidationMapService(BindingResult result);

}
