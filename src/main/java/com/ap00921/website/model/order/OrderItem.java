/**
 * OrderItem.java
 */
package com.ap00921.website.model.order;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class OrderItem {

	@NotEmpty(message = "Item product ID cannot be blank")
	@Getter
	@Setter
	private String productId;
	
	@NotEmpty(message = "Item variation ID cannot be blank")
	@Getter
	@Setter
	private String variationId;
	
	@NotEmpty(message = "Item colour ID cannot be blank")
	@Getter
	@Setter
	private String colourId;
	
	@NotEmpty(message = "Item gender cannot be blank")
	@Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Size(min = 3, message = "Brand must be longer that 3 characters")
	@Size(max = 16, message = "Brand must be less than 16 characters")
	@Getter
	@Setter
	private String itemBrand;
	
	@NotEmpty(message = "Item name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Size(min = 3, message = "Name must be longer than 3 characters")
	@Size(max = 64, message = "Name must be less than 64 characters")
	@Getter
	@Setter
	private String itemName;
	
	@NotEmpty(message = "Item color cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters")
	@Size(min = 3, message = "Color must be longer that 3 characters")
	@Size(max = 16, message = "Color must be less than 16 characters")
	@Getter
	@Setter
	private String itemColour;
	
	@NotEmpty(message = "Size cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters")
	@Size(min = 1, message = "Size must be longer that 1 characters")
	@Size(max = 16, message = "Size must be less than 16 characters")
	@Getter
	@Setter
	private String itemSize;
	
	@NotNull(message = "Price cannot be blank")
	@DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0.1")
	@Getter
	@Setter
	private double itemPrice;
	
	@NotNull(message = "Item quantity cannot be blank")
	@Getter
	@Setter
	private double itemQuantity;

	/**
	 * @param productId
	 * @param variationId
	 * @param colourId
	 * @param itemBrand
	 * @param itemName
	 * @param itemColour
	 * @param itemSize
	 * @param itemPrice
	 * @param itemQuantity
	 */
	public OrderItem(@NotEmpty(message = "Item product ID cannot be blank") String productId,
			@NotEmpty(message = "Item variation ID cannot be blank") String variationId,
			@NotEmpty(message = "Item colour ID cannot be blank") String colourId,
			@NotEmpty(message = "Item gender cannot be blank") @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -") @Size(min = 3, message = "Brand must be longer that 3 characters") @Size(max = 16, message = "Brand must be less than 16 characters") String itemBrand,
			@NotEmpty(message = "Item name cannot be blank") @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -") @Size(min = 3, message = "Name must be longer than 3 characters") @Size(max = 64, message = "Name must be less than 64 characters") String itemName,
			@NotEmpty(message = "Item color cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters") @Size(min = 3, message = "Color must be longer that 3 characters") @Size(max = 16, message = "Color must be less than 16 characters") String itemColour,
			@NotEmpty(message = "Size cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters") @Size(min = 1, message = "Size must be longer that 1 characters") @Size(max = 16, message = "Size must be less than 16 characters") String itemSize,
			@NotEmpty(message = "Item price cannot be blank") @NotNull(message = "Price cannot be blank") @DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0.1") double itemPrice,
			@NotEmpty(message = "Item quantity cannot be blank") double itemQuantity) {
		super();
		this.productId = productId;
		this.variationId = variationId;
		this.colourId = colourId;
		this.itemBrand = itemBrand;
		this.itemName = itemName;
		this.itemColour = itemColour;
		this.itemSize = itemSize;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
	}
	
	
	
}
