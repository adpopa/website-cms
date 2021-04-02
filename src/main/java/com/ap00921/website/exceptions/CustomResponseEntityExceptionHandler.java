/**
 * CustomResponseEntityExceptionHandler.java
 */
package com.ap00921.website.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ap00921.website.exceptions.colour.ColourIdException;
import com.ap00921.website.exceptions.colour.ColourIdExceptionResponse;
import com.ap00921.website.exceptions.product.ProductIdException;
import com.ap00921.website.exceptions.product.ProductIdExceptionResponse;
import com.ap00921.website.exceptions.product.ProductNameException;
import com.ap00921.website.exceptions.product.ProductNameExceptionResponse;
import com.ap00921.website.exceptions.user.UsernameException;
import com.ap00921.website.exceptions.user.UsernameExceptionResponse;
import com.ap00921.website.exceptions.variation.VariationIdException;
import com.ap00921.website.exceptions.variation.VariationIdExceptionResponse;
import com.ap00921.website.exceptions.variation.VariationStockException;
import com.ap00921.website.exceptions.variation.VariationStockExceptionResponse;

/**
 * @author Alex Daniel Popa
 *
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	/* Users Exceptions	 */
	@ExceptionHandler
	public final ResponseEntity<Object> handleUsernameException(UsernameException exception, WebRequest request) {
		UsernameExceptionResponse exceptionResponse = new UsernameExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/* End of Users Exceptions	 */
	
	/* Products exceptions */
	@ExceptionHandler
	public final ResponseEntity<Object> handleProductNameException(ProductNameException exception, WebRequest request) {
		ProductNameExceptionResponse exceptionResponse = new ProductNameExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleProductIdException(ProductIdException exception, WebRequest request) {
		ProductIdExceptionResponse exceptionResponse = new ProductIdExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/* End of Products exceptions */
	
	/* Colour exceptions */
	@ExceptionHandler
	public final ResponseEntity<Object> handleColourIdException(ColourIdException exception, WebRequest request) {
		ColourIdExceptionResponse exceptionResponse = new ColourIdExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/* End of Colour exceptions */
	
	/* Variations exceptions */
	@ExceptionHandler
	public final ResponseEntity<Object> handleVariationIdException(VariationIdException exception, WebRequest request) {
		VariationIdExceptionResponse exceptionResponse = new VariationIdExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/* End of Variations exceptions */
	
	/* Stock Exceptions */
	@ExceptionHandler
	public final ResponseEntity<Object> handleUpdateStock(VariationStockException exception, WebRequest request) {
		VariationStockExceptionResponse exceptionResponse = new VariationStockExceptionResponse(exception.getMessage());
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	/* End of Stock Exceptions */

}
