/**
 * RoleServiceImplIntegrationTest.java
 */
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
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.RoleRepository;
import com.ap00921.website.service.RoleService;
import com.ap00921.website.service.impl.RoleServiceImpl;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
public class RoleServiceImplUnitTest {
	
    @TestConfiguration
    static class MyUserDetailsServiceImplUnitTestConfiguration {
        @Bean
        public RoleService roleService() {
            return new RoleServiceImpl();
        }
    }

	@Autowired
	private RoleService roleService;
	
	@MockBean
	private RoleRepository roleRepository;
	
	private Role role = null;
	
	@Before
	public void before() {
		role = new Role("role");
		
		Mockito.when(roleRepository.insert(role)).thenReturn(role);
		Mockito.when(roleRepository.findByRoleId(role.getRoleId())).thenReturn(role);
	}
	@Test
	public void whenInserValidRole_thenRoleShouldBeReturned() {
		
		// when
		Role returned = roleService.insert(role);
		
		// then
		assertThat(returned.getRoleType()).isEqualTo(role.getRoleType());
	}
	
	@Test
	public void whenGetByRoleId_thenRoleShouldBeReturned() {
		// when
		Role returned = roleService.getByRoleId(role.getRoleId());
		
		// then
		assertThat(returned.getRoleId()).isEqualTo(role.getRoleId());
	}
	
}
