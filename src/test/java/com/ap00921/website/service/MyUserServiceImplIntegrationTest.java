/**
 * MyUserServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.repository.MyUserRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyUserServiceImplIntegrationTest {

	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private MyUserService userService;
	
	private MyUser user = null;
	
	@Before
	public void before() {
		userRepository.deleteAll();
		
		user = new MyUser("username", "password", "password");
		
		userRepository.insert(user);
	}
	
	@After
	public void after() {
		userRepository.deleteAll();
	}
	
	@Test
	public void whenGetAllUsers_thenListOfUsersShouldBeReturned() {
		// when
		List<MyUser> returned = userService.getAll();
		
		// then
		assertThat(returned.get(0).getUserId()).isEqualTo(user.getUserId());
	}
	
	@Test
	public void whenRegisterValidUser_thenUserShouldBeReturned() {
		// given
		this.after();
		
		// when
		MyUser returned = userService.register(user);
		
		// then
		assertThat(returned.getUserId()).isEqualTo(user.getUserId());
	}
	
	@Test
	public void whenGetByUsername_thenUserShouldBeReturned() {
		// when
		MyUser returned = userService.findByUsername(user.getUsername());
		
		// then
		assertThat(returned.getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	public void whenInsertAdminValidUser_thenAdminUserShouldBeReturned() {
		userRepository.deleteAll();
		
		// when
		MyUser returned = userService.insertAdmin(user);
		
		// then
		assertThat("ROLE_ADMIN").isEqualTo(returned.getRole().getRoleType());
	}
	
	
}
