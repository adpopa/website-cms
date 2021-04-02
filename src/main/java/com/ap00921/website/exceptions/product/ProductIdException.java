/**
 * ProductIdException.java
 */
package com.ap00921.website.exceptions.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alex Daniel Popa
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductIdException extends RuntimeException {
	
	public ProductIdException(String message) {
		super(message);
	}

}
