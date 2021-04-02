/**
 * InvalidLoginResponse.java
 */
package com.ap00921.website.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class InvalidLoginResponse {

	@Getter
	@Setter
	private String username;
	
	@Getter
	@Setter
	private String password;

	public InvalidLoginResponse() {
		this.username = "Invalid username";
		this.password = "Invalid password";
	}	
	
}
