/**
 * MyUserControllerIntegrationTest.java
 */
package com.ap00921.website.controller.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserService;
import com.google.gson.Gson;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MyUserControllerIntegrationTest {

	public final static String PATH = "/api/credentials";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private MyUserService userService;
	
	@Autowired
	private MyUserRepository userRepository;

	private MyUser user = null;
	
	@Before
	public void before() {
		userRepository.deleteAll();
		
		user = new MyUser("user@email.com", "password", "password");
		
		userService.register(user);
	}
	
	@After
	public void after() {
		userRepository.deleteAll();
	}
	
	@Test
	public void whenValidCredentials_thenReceiveTokenAndStatus200() throws Exception {
		// when 
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "user@email.com");
		credentials.put("password", "password");
		
		// then
		mockMvc.perform(post(PATH + "/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(credentials)))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.success", is(true)));
	}
	
	@Test
	public void whenValidInput_thenCreateUserAndStatus201() throws Exception {
		// when
		Map<String, String> credentials = new HashMap<>();
		credentials.put("username", "username@email.com");
		credentials.put("password", "password");
		credentials.put("confirmPassword", "password");
		
		//then
		mockMvc.perform(post(PATH + "/register").contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(credentials)))
//				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.username", is("username@email.com")));
		
	}
}
