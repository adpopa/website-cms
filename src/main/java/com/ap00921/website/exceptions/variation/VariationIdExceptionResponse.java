/**
 * ProductIdExceptionResponse.java
 */
package com.ap00921.website.exceptions.variation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class VariationIdExceptionResponse {
	
	@Getter
	@Setter
	private String variationId;

	public VariationIdExceptionResponse(String variationId) {
		this.variationId = variationId;
	}
}
