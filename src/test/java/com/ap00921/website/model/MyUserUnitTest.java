/**
 * MyUserUnitTest.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
public class MyUserUnitTest {
	
	private MyUser user = null;
	
	@Test
	public void testAccessorsMutators() {
		 user = new MyUser("username@email.com", "password", "password");
		
		assertEquals(user.getUsername(), "username@email.com");
		assertEquals(user.getPassword(), "password");
		assertEquals(user.getConfirmPassword(), "password");
	}

}
