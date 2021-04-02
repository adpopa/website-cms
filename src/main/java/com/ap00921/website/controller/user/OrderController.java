/**
 * OrderController.java
 */
package com.ap00921.website.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.service.OrderService;
import com.ap00921.website.service.ValidationMapErrorService;

/**
 * @author Alex Daniel Popa
 *
 */
@RestController
@RequestMapping("/api/user/orders")
@CrossOrigin
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ValidationMapErrorService validationMapErrorService;
	
	@GetMapping("/all")
	public List<Order> getAll() {
		List<Order> orders = this.orderService.getAllOrders();
		return orders;
	}
	
	@PostMapping
	public ResponseEntity<?> insertOrder(@Valid @RequestBody Order order, BindingResult result) {

		ResponseEntity<?> error = validationMapErrorService.ValidationMapService(result);
		
		if (error != null)
			return error;

		Order newOrder = orderService.insert(order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.CREATED);
	}
	
}
