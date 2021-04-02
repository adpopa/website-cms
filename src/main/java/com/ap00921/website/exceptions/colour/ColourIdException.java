/**
 * ColourIdException.java
 */
package com.ap00921.website.exceptions.colour;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alex Daniel Popa
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ColourIdException extends RuntimeException{
	
	public ColourIdException(String message) {
		super(message);
	}
}
