/**
 * UsernameExceptionResponse.java
 */
package com.ap00921.website.exceptions.user;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class UsernameExceptionResponse {
	
	@Getter
	@Setter
	private String username;

	public UsernameExceptionResponse(String username) {
		this.username = username;
	}
}
