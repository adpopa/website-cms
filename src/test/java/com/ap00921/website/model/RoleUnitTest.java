/**
 * RoleUnitTest.java
 */
package com.ap00921.website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ap00921.website.model.user.Role;

/**
 * @author Alex Daniel Popa
 *
 */
public class RoleUnitTest {

	private Role role = null;
	
	@Test
	public void testAccessorsMutators() {
		 role = new Role("ROLE_USER");
		
		assertEquals(role.getRoleType(), "ROLE_USER");
	}
	
}
