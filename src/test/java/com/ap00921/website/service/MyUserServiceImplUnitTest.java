/**
 * MyUserServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserService;
import com.ap00921.website.service.RoleService;
import com.ap00921.website.service.impl.MyUserServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class MyUserServiceImplUnitTest {
	
    @TestConfiguration
    static class MyUserServiceImplUnitTestContextConfiguration {
        @Bean
        public MyUserService userService() {
            return new MyUserServiceImpl();
        }
        
        @Bean
        public PasswordEncoder encoder() {
        	return new BCryptPasswordEncoder();
        }
    }

    @Autowired
	private MyUserService userService;
	
	@MockBean
	private MyUserRepository userRepository;
	
	@MockBean
	private RoleService roleService;
	
	private MyUser user = null;
	
	@Before
	public void before() {
		Role role_user = new Role("ROLE_USER");
		Role role_admin = new Role("ROLE_ADMIN");
		
		Mockito.when(roleService.getByRoleType("ROLE_USER")).thenReturn(role_user);
		Mockito.when(roleService.getByRoleType("ROLE_ADMIN")).thenReturn(role_admin);
		
		user = new MyUser("username", "password", "password");
		
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(user));
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(userRepository.insert(user)).thenReturn(user);
	}
	
	@Test
	public void whenGetAllUsers_thenListOfUsersShouldBeReturned() {
		// when
		List<MyUser> returned = userService.getAll();
		
		// then
		assertThat(returned.get(0).getUserId()).isEqualTo(user.getUserId());
	}
	
	
	@Test
	public void whenFindByUsername_thenUserShouldBeReturned() {
		// when
		MyUser returned = userService.findByUsername(user.getUsername());
		
		// then
		assertThat(returned.getUsername()).isEqualTo(user.getUsername());
	}
	
	@Test
	public void whenRegisterValidUser_thenUserShouldBeReturned() {
		// when
		MyUser returned = userService.register(user);
		
		// then
		assertThat(returned.getUserId()).isEqualTo(user.getUserId());
	}

	
	@Test
	public void whenInsertAdminValidUser_thenAdminUserShouldBeReturned() {
		// when
		MyUser returned = userService.insertAdmin(user);
		
		// then
		assertThat("ROLE_ADMIN").isEqualTo(returned.getRole().getRoleType());
	}
	
	
}
