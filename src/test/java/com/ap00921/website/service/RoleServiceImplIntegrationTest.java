/**
 * RoleServiceImplIntegrationTest.java
 */
package com.ap00921.website.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.RoleRepository;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplIntegrationTest {
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;
	
	private Role role = null;
	
	@Before
	public void before() {
		roleRepository.deleteAll();
		
		role = new Role("role");
		
		roleRepository.insert(role);
	}
	
	@After
	public void after() {
		roleRepository.deleteAll();
	}
	
	@Test
	public void whenInserValidRole_thenRoleShouldBeReturned() {
		// given
		this.after();
		
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
