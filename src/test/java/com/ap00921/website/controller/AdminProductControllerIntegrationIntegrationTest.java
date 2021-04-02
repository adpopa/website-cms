/**
 * AdminProductControllerIntegrationIntegrationTest.java
 */
package com.ap00921.website.controller;

import static com.ap00921.website.security.SecurityConstants.SECRET_KEY;
import static com.ap00921.website.security.SecurityConstants.EXPIRATION_TIME;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Product;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.repository.ProductRepository;
import com.ap00921.website.repository.RoleRepository;
import com.ap00921.website.repository.VariationRepository;
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
public class AdminProductControllerIntegrationIntegrationTest {

	public final static String PATH = "/api/admin/products";
	
	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private VariationRepository variationRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private MyUserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static Product product = null;
	
	private static String token = null;
	
	@Before
	public void before() throws UnsupportedEncodingException, Exception {
		productRepository.deleteAll();
		variationRepository.deleteAll();
		colourRepository.deleteAll();
		roleRepository.deleteAll();
		userRepository.deleteAll();
		
		product = new Product(	"Men",
				"Hugo",
				"Sweater",
				"Logo Sweater", 
				25.00, 
				Arrays.asList(
						new Colour("Red", Arrays.asList( 
								new Variation("S", 67),
								new Variation("M", 60),
								new Variation("L", 50))
						),
						new Colour("Black", Arrays.asList( 
						new Variation("S", 43),
						new Variation("M", 64),
						new Variation("L", 34))
						)
					)
				);

		Role role = new Role("ROLE_ADMIN");
		roleRepository.insert(role);
		
		MyUser user = new MyUser("username@email.com", "password", "password");
		user = userService.insertAdmin(user);
		
		Date now = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("id", user.getUserId());
		claims.put("username", user.getUsername());
		claims.put("authorities", "ROLE_ADMIN");
		
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
		productRepository.deleteAll();
		variationRepository.deleteAll();
		colourRepository.deleteAll();
		roleRepository.deleteAll();
		userRepository.deleteAll();
	}
	
	@Test
	public void whenValidProduct_thenCreateProductAndStatus201() throws Exception {

		mockMvc.perform(post(PATH)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product)))
				.andExpect(status().isCreated())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productId", is(product.getProductId())));
		
	}
	
	@Test
	public void whenValidProduct_thenUpdateProductAndStatus201() throws Exception {
		productRepository.save(product);
		product.setProductBrand("update");
		
		mockMvc.perform(put(PATH)
				.header("Authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(product)))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productBrand", is(product.getProductBrand())));
	}
	
	@Test
	public void whenValidProduct_thenDeleteProductAndStatus200() throws Exception {
		productRepository.save(product);
		
		mockMvc.perform(delete(PATH + "/" + product.getProductId())
				.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk());
	}
}
