/**
 * LoginRequest.java
 */
package com.ap00921.website.payload;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class LoginRequest {

	@NotEmpty(message = "Username is required")
	@Getter
	@Setter
	private String username;
	
	@NotEmpty(message = "Password is required")
	@Getter
	@Setter
	private String password;

	
}
