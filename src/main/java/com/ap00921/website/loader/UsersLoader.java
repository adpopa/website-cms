/**
 * UsersDao.java
 */
package com.ap00921.website.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserService;

/**
 * @author Alex Daniel Popa
 *
 */
//@Component
public class UsersLoader implements CommandLineRunner {

	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private MyUserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		
		this.userRepository.deleteAll();
		MyUser user = new MyUser();
		user.setUsername("user@email.com");
		user.setPassword("1userPassword!");
		
		System.out.println("****** insert user");
		this.userService.register(user);
		
		MyUser admin = new MyUser();
		admin.setUsername("admin@email.com");
		admin.setPassword("1adminPassword!");
		
		System.out.println("****** insert admin");
		this.userService.insertAdmin(admin);
		
	}

}
