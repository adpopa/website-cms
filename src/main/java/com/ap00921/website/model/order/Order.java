/**
 * Order.java
 */
package com.ap00921.website.model.order;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "orders")
public class Order {
	
	@Id
	@Getter
	private String orderId;
	
	@NotEmpty(message = "Order user id cannot be empty")
	@Getter
	@Setter
	private String userId;
	
	@Getter
	@Setter
	private @Valid OrderAddress address;
	
	@NotEmpty(message = "Order items cannot be blank")
	@Getter
	@Setter
	private List<@Valid OrderItem> items;
	
	@Getter
	@Setter
	private double total;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@CreatedDate
	@Getter
	@Setter
	private Date createdAt;

	/**
	 * @param orderId
	 * @param userId
	 * @param address
	 * @param items
	 * @param total
	 * @param createdAt
	 */
	public Order(@NotEmpty(message = "Order user id cannot be empty") String userId,
			@Valid OrderAddress address,
			@NotEmpty(message = "Order items cannot be blank") List<@Valid OrderItem> items, double total) {
		super();
		this.orderId = new ObjectId().toHexString();
		this.userId = userId;
		this.address = address;
		this.items = items;
		this.total = total;
	}

}