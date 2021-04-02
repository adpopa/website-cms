package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserDetailsService;
import com.ap00921.website.service.impl.MyUserDetailsServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class MyUserDetailsServiceImplUnitTest {

    @TestConfiguration
    static class MyUserDetailsServiceImplUnitTestConfiguration {
        @Bean
        public MyUserDetailsService userDetailsService() {
            return new MyUserDetailsServiceImpl();
        }
    }
    
	@MockBean
	private MyUserRepository userRepository;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	private MyUser user = null;
	
	@Before
	public void before() {
		userRepository.deleteAll();
		
		user = new MyUser("username", "password", "password");
		user.setRole(new Role("ROLE_USER"));
		
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
		Mockito.when(userRepository.getByUserId(user.getUserId())).thenReturn(user);
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
