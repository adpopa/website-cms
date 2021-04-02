/**
 * RoleServiceImpl.java
 */
package com.ap00921.website.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.RoleRepository;
import com.ap00921.website.service.RoleService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role insert(Role role) {
		return this.roleRepository.insert(role);
	}
	
	@Override
	public Role getByRoleId(String roleId) {
		Role role = this.roleRepository.findByRoleId(roleId);
		
//		if(role == null) {
//			throw new RoleIdException("The role with id '" + roleId + "' does not exist");
//		}
		
		return role;
	}

	@Override
	public Role getByRoleType(String roleType) {
		Role role = this.roleRepository.findByRoleType(roleType);
		
//		if(role == null) {
//			throw new RoleNameException("The role with name '" + roleType + "' does not exist");
//		}
		
		return role;
	}

}
