/**
 * RoleRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ap00921.website.model.user.Role;

/**
 * @author Alex Daniel Popa
 *
 */
public interface RoleRepository extends MongoRepository<Role, String> {
	
	Role findByRoleId(String roleId);
	
//	@Query(fields = "{'name' : 1}")
	Role findByRoleType(String roleType);
}
