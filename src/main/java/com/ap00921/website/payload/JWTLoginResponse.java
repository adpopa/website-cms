/**
 * JWTLoginResponse.java
 */
package com.ap00921.website.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class JWTLoginResponse {

	@Getter
	@Setter
	private boolean success;
	
	@Getter
	@Setter
	private String token;

	/**
	 * @param success
	 * @param token
	 */
	public JWTLoginResponse(boolean success, String token) {
		super();
		this.success = success;
		this.token = token;
	}
	
    @Override
    public String toString() {
        return "JWTLoginResponse{" +
                "success=" + success +
                ", token='" + token + '\'' +
                '}';
    }
	
}
