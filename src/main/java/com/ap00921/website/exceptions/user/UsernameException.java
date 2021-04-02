/**
 * UsernameException.java
 */
package com.ap00921.website.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Alex Daniel Popa
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameException extends RuntimeException {

	public UsernameException(String message) {
		super(message);
	}

}
