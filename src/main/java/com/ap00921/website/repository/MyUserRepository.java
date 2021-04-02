/**
 * UserRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
@Repository
public interface MyUserRepository extends MongoRepository<MyUser, String> {
	
	MyUser findByUsername(String username);
	
	MyUser getByUserId(String userId);
}
