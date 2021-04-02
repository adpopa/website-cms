/**
 * VariationIdException.java
 */
package com.ap00921.website.exceptions.variation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alex Daniel Popa
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VariationIdException extends RuntimeException {
	
	public VariationIdException(String message) {
		super(message);
	}
	
}
