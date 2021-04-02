/**
 * QuantityInStock.java
 */
package com.ap00921.website.model.product;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "variations")
public class Variation {

	@Id
	@Getter
	private String variationId;

	@NotEmpty(message = "Size cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters")
	@Size(min = 1, message = "Size must be longer that 1 characters")
	@Size(max = 16, message = "Size must be less than 16 characters")
	@Getter
	@Setter
	private String productSize;

	@NotNull(message = "Quantity cannot be blank")
	@Getter
	@Setter
	private Integer productQuantity;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
	@Getter
	@Setter
	private Date createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	@Getter
	@Setter
	private Date updatedAt;

	/**
	 * @param productColor
	 * @param productSize
	 * @param productQuantity
	 */
	public Variation(
			@NotEmpty(message = "Size cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters") @Size(min = 1, message = "Size must be longer that 1 characters") @Size(max = 5, message = "Size must be less than 5 characters") String productSize,
			@NotNull(message = "Quantity cannot be blank") Integer productQuantity) {
		this.variationId = new ObjectId().toHexString();
		this.productSize = productSize;
		this.productQuantity = productQuantity;
	}

	public boolean equals(Variation variation) {
		if (!this.variationId.equals(variation.getVariationId()))
			return false;

		if (!this.productSize.equals(variation.getProductSize()))
			return false;

		if (!this.productQuantity.equals(variation.getProductQuantity()))
			return false;

		return true;
	}

}
