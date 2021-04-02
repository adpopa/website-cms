/**
 * VariationStockExceptionResponse.java
 */
package com.ap00921.website.exceptions.variation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class VariationStockExceptionResponse {

	@Getter
	@Setter
	private String variationStock;

	public VariationStockExceptionResponse(String variationStock) {
		this.variationStock = variationStock;
	}
}
