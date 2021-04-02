/**
 * RoleLoader.java
 */
package com.ap00921.website.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.RoleRepository;
import com.ap00921.website.service.RoleService;

/**
 * @author Alex Daniel Popa
 *
 */
//@Component
public class RoleLoader implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public void run(String... args) throws Exception {
	
		Role USER = new Role("ROLE_USER");
		Role ADMIN = new Role("ROLE_ADMIN");
		
		this.roleRepository.deleteAll();
		
		this.roleService.insert(USER);
		this.roleService.insert(ADMIN);
		
	}
}
