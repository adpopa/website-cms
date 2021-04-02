/**
 * RoleService.java
 */
package com.ap00921.website.service;

import com.ap00921.website.model.user.Role;

/**
 * @author Alex Daniel Popa
 *
 */
public interface RoleService {

	public Role insert(Role role);
	
	public Role getByRoleId(String roleId);
	
	public Role getByRoleType(String roleType);
}
