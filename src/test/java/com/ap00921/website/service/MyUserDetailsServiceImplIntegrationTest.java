package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.repository.RoleRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserDetailsServiceImplIntegrationTest {
	
	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private MyUserService userService;
	
	private MyUser user = null;
	
	@Before
	public void before() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
		
		user = new MyUser("user@email.com", "password", "password");
		
		Role role = new Role("ROLE_USER");
		roleRepository.insert(role);
		
		userService.register(user);
	}
	
	@After
	public void after() {
		userRepository.deleteAll();
		roleRepository.deleteAll();
	}
	
	@Test
	public void whenLoadByValidUsernamee_thenUserShouldBeReturned() {
		// when
		UserDetails returned = userDetailsService.loadUserByUsername(user.getUsername());
		
		// then
		assertThat(returned.getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	public void whenLoadUserbyValidUserId_thenUserShouldBeReturned() {
		// when
		MyUser returned = userDetailsService.loadUserbyUserId(user.getUserId());

		// then
		assertThat(returned.getUserId()).isEqualTo(user.getUserId());
	}
	
}
