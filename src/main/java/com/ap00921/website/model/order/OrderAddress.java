/**
 * Adress.java
 */
package com.ap00921.website.model.order;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
public class OrderAddress {

	@NotEmpty(message = "Name cannot be blank")
	@Getter
	@Setter
	private String name;
	
	@NotEmpty(message = "Name cannot be blank")
	@Pattern(regexp = "(((\\+44)? ?(\\(0\\))? ?)|(0))( ?[0-9]{3,4}){3}", 
			message = "Phone number should contain only numbers. e.g: +44 1234 567890 or 01234 567890")
	@Getter
	@Setter
	private String phone;
	
	@NotEmpty(message = "Address cannot be blank")
	@Pattern(regexp = "\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?", 
			message = "Please match the format requested. e.g: 1234 Main St")
	@Getter
	@Setter
	private String adress1;
	
	@NotEmpty(message = "Name cannot be blank") 
	@Getter
	@Setter
	private String adress2;
	
	@NotEmpty(message = "Name cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", 
			message = "Please match the format requested. e.g: London")
	@Getter
	@Setter
	private String city;
	
	@NotEmpty(message = "Name cannot be blank")
	@Pattern(regexp = "^(GIR ?0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]([0-9ABEHMNPRV-Y])?)|[0-9][A-HJKPS-UW]) ?[0-9][ABD-HJLNP-UW-Z]{2})$",
			message = "Please match the format requested. e.g: AB1 2CD")
	@Getter
	@Setter
	private String postcode;
	
	@NotEmpty(message = "Name cannot be blank")
	@Pattern(regexp = "[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Characters allowed: alphanumeric characters, space, and -")
	@Getter
	@Setter
	private String country;
	
	@Getter
	@Setter
	private boolean saveAdress;


	
	/**
	 * @param name
	 * @param phone
	 * @param adress1
	 * @param adress2
	 * @param city
	 * @param postcode
	 * @param country
	 */
	public OrderAddress(@NotEmpty(message = "Name cannot be blank") String name,
			@NotEmpty(message = "Name cannot be blank") @Pattern(regexp = "(((\\+44)? ?(\\(0\\))? ?)|(0))( ?[0-9]{3,4}){3}", message = "Phone number should contain only numbers. e.g: +44 1234 567890 or 01234 567890") String phone,
			@NotEmpty(message = "Address cannot be blank") @Pattern(regexp = "\\d+[ ](?:[A-Za-z0-9.-]+[ ]?)+(?:Avenue|Lane|Road|Boulevard|Drive|Street|Ave|Dr|Rd|Blvd|Ln|St)\\.?", message = "Please match the format requested. e.g: 1234 Main St") String adress1,
			@NotEmpty(message = "Name cannot be blank") String adress2,
			@NotEmpty(message = "Name cannot be blank") @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Please match the format requested. e.g: London") String city,
			@NotEmpty(message = "Name cannot be blank") @Pattern(regexp = "^(GIR ?0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]([0-9ABEHMNPRV-Y])?)|[0-9][A-HJKPS-UW]) ?[0-9][ABD-HJLNP-UW-Z]{2})$", message = "Please match the format requested. e.g: AB1 2CD") String postcode,
			@NotEmpty(message = "Name cannot be blank") @Pattern(regexp = "[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Characters allowed: alphanumeric characters, space, and -") String country) {
		super();
		this.name = name;
		this.phone = phone;
		this.adress1 = adress1;
		this.adress2 = adress2;
		this.city = city;
		this.postcode = postcode;
		this.country = country;
	}




	public OrderAddress() {
	}
	
	
	
	
	
}
