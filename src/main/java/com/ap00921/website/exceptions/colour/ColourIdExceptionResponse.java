package com.ap00921.website.exceptions.colour;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class ColourIdExceptionResponse {
	
	@Getter
	@Setter
	private String colourId;

	public ColourIdExceptionResponse(String colourId) {
		this.colourId = colourId;
	}
	
}
