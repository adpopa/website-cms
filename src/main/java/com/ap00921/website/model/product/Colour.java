/**
 * Color.java
 */
package com.ap00921.website.model.product;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "colours")
public class Colour {

	@Id
	@Getter
	private String colourId;

	@NotEmpty(message = "Color cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters")
	@Size(min = 3, message = "Color must be longer that 3 characters")
	@Size(max = 16, message = "Color must be less than 16 characters")
	@Getter
	@Setter
	private String productColour;
	
	@NotEmpty(message = "Product variations cannot be blank")
	@Getter
	@Setter
	@DBRef
	private List<@Valid Variation> productVariations;
	
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
	 * @param colorId
	 * @param productColor
	 * @param productVariations
	 */
	public Colour(
			@NotEmpty(message = "Color cannot be blank") @Pattern(regexp = "^[a-zA-Z]+$", message = "Characters allowed: alphabetic characters") @Size(min = 3, message = "Color must be longer that 3 characters") @Size(max = 16, message = "Color must be less than 16 characters") String productColour,
			@NotEmpty(message = "Product variations cannot be blank") List<@Valid Variation> productVariations) {
		this.colourId = new ObjectId().toHexString();
		this.productColour = productColour;
		this.productVariations = productVariations;
	}
	
}
