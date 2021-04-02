/**
 * OrderControllerIntegrationTest.java
 */
package com.ap00921.website.controller;

import static com.ap00921.website.security.SecurityConstants.EXPIRATION_TIME;
import static com.ap00921.website.security.SecurityConstants.SECRET_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ap00921.website.model.order.Order;
import com.ap00921.website.model.order.OrderAddress;
import com.ap00921.website.model.order.OrderItem;
import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.repository.OrderRepository;
import com.ap00921.website.repository.RoleRepository;
import com.ap00921.website.service.MyUserService;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

	public final static String PATH = "/api/user/orders";
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private MyUserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static Order order = null;
	
	private static String token = null;
	
	@Before
	public void before() {
		roleRepository.deleteAll();
		userRepository.deleteAll();
		orderRepository.deleteAll();
		
		String string = "string";
		
		OrderAddress address = null;
		address = new OrderAddress(string, "07493606161", "1 Guildford Park Avenue", string, "GU2 7NJ", string, string);
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item = new OrderItem(string, string, string, string, string, string, string, 10, 10);
		items.add(item);
		
		order = new Order("userId", address, items, 10);
		
		Role role = new Role("ROLE_USER");
		roleRepository.insert(role);
		
		MyUser user = new MyUser("username@email.com", "password", "password");
		user = userService.register(user);
		
		Date now = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("id", user.getUserId());
		claims.put("username", user.getUsername());
		claims.put("authorities", "ROLE_USER");
		
		token = Jwts.builder()
				.setSubject(user.getUserId())
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	@After
	public void after() {
		roleRepository.deleteAll();
		userRepository.deleteAll();
		orderRepository.deleteAll();
	}
	
	@Test
	public void whenValidOrder_thenCreateOrderAndStatus201() throws Exception {

		mockMvc.perform(post(PATH)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(order)))
				.andExpect(status().isBadRequest());
	}
	
}
