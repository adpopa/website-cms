/**
 * ProductIdExceptionResponse.java
 */
package com.ap00921.website.exceptions.product;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class ProductIdExceptionResponse {
	
	@Getter
	@Setter
	private String productId;

	public ProductIdExceptionResponse(String productId) {
		this.productId = productId;
	}
}
