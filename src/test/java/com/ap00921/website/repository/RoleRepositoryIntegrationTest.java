/**
 * RoleRepositoryIntegrationTest.java
 */
package com.ap00921.website.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ap00921.website.model.user.Role;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class RoleRepositoryIntegrationTest {

	@Autowired
	private RoleRepository roleRepostory;

	@Before
	public void before() {
		roleRepostory.deleteAll();
	}

	@Test
	public void whenGetByRoleId_thenReturnRole() {
		// given
		Role role = new Role("ROLE_USER");
		roleRepostory.save(role);

		// when
		Role found = roleRepostory.findByRoleId(role.getRoleId());

		// then
		assertThat(found.getRoleId()).isEqualTo(role.getRoleId());
	}

	@Test
	public void whenfindByRoleType_thenReturnRole() {
		// given
		Role role = new Role("ROLE_USER");
		roleRepostory.save(role);

		// when
		Role found = roleRepostory.findByRoleType(role.getRoleType());

		// then
		assertThat(found.getRoleType()).isEqualTo(role.getRoleType());
	}

	@After
	public void after() {
		roleRepostory.deleteAll();
	}

}
