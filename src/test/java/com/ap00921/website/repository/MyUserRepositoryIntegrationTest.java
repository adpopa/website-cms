/**
 * MyUserRepositoryIntegrationTest.java
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

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class MyUserRepositoryIntegrationTest {

	@Autowired
	private MyUserRepository userRepostory;
	
	@Before
	public void before() {
		userRepostory.deleteAll();
	}
	
	@Test
	public void whenGetByMyUserId_thenReturnMyUser() {
		// given
		MyUser user = new MyUser("username@email.com", "password", "password");
		userRepostory.save(user);
		
		// when
		MyUser found = userRepostory.getByUserId(user.getUserId());
		
		// then
		assertThat(found.getUserId()).isEqualTo(user.getUserId());
	}
	
	@Test
	public void whenfindByUsername_thenReturnMyUser() {
		// given
		MyUser user = new MyUser("username@email.com", "password", "password");
		userRepostory.save(user);
		
		// when
		MyUser found = userRepostory.findByUsername(user.getUsername());
		
		// then
		assertThat(found.getUsername()).isEqualTo(user.getUsername());
	}
	
	@After
	public void after() {
		userRepostory.deleteAll();
	}
}
