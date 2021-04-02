/**
 * ProductNameExceptionResponse.java
 */
package com.ap00921.website.exceptions.product;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class ProductNameExceptionResponse {

	@Getter
	@Setter
	private String productName;

	public ProductNameExceptionResponse(String productName) {
		this.productName = productName;
	}
	
	
	
}
