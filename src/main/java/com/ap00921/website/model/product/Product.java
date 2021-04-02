/**
 * Product.java
 */
package com.ap00921.website.model.product;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "products")
public class Product {

	@Id
	@Getter
	private String productId;
	
	@NotEmpty(message = "Gender cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Size(min = 3, message = "Gender must be longer that 3 characters")
	@Size(max = 6, message = "Gender must be less than 6 characters")
	@Getter
	@Setter
	private String productGender;

	@NotEmpty(message = "Brand cannot be blank")
	@Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Size(min = 3, message = "Brand must be longer that 3 characters")
	@Size(max = 16, message = "Brand must be less than 16 characters")
	@Getter
	@Setter
	private String productBrand;
	
	@NotEmpty(message = "Type cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters")
	@Size(min = 3, message = "Type must be longer that 3 characters")
	@Size(max = 16, message = "Type must be less than 16 characters")
	@Getter
	@Setter
	private String productType;

	@NotEmpty(message = "Name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Size(min = 3, message = "Name must be longer than 3 characters")
	@Size(max = 64, message = "Name must be less than 64 characters")
	@Indexed(unique = true)
	@Getter
	@Setter
	private String productName;

	@NotNull(message = "Price cannot be blank")
	@DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0.1")
	@Getter
	@Setter
	private Double productPrice;
	
	@NotEmpty(message = "Product colors cannot be blank")
	@Getter
	@Setter
	@DBRef
	private List<@Valid Colour> productColours;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreatedDate
	@Getter
	@Setter
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@LastModifiedDate
	@Getter
	@Setter
	private Date updatedAt;

	/**
	 * @param productId
	 * @param productGender
	 * @param productBrand
	 * @param productType
	 * @param productName
	 * @param productPrice
	 * @param productColors
	 */
	public Product(
			@NotEmpty(message = "Gender cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphanumeric characters, space, and -") @Size(min = 3, message = "Gender must be longer that 3 characters") @Size(max = 6, message = "Gender must be less than 6 characters") String productGender,
			@NotEmpty(message = "Brand cannot be blank") @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -") @Size(min = 3, message = "Brand must be longer that 3 characters") @Size(max = 16, message = "Brand must be less than 16 characters") String productBrand,
			@NotEmpty(message = "Type cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters") @Size(min = 3, message = "Type must be longer that 3 characters") @Size(max = 16, message = "Type must be less than 16 characters") String productType,
			@NotEmpty(message = "Name cannot be blank") @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Characters allowed: alphanumeric characters, space, and -") @Size(min = 3, message = "Name must be longer than 3 characters") @Size(max = 64, message = "Name must be less than 64 characters") String productName,
			@NotNull(message = "Price cannot be blank") @DecimalMin(value = "0.1", inclusive = true, message = "Price must be greater than 0.1") Double productPrice,
			@NotEmpty(message = "Product colors cannot be blank") List<@Valid Colour> productColours) {
		this.productId = new ObjectId().toHexString();
		this.productGender = productGender;
		this.productBrand = productBrand;
		this.productType = productType;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productColours = productColours;
	}

	
}
