/**
 * MyUserServiceImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ap00921.website.exceptions.user.UsernameException;
import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.model.user.Role;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserService;
import com.ap00921.website.service.RoleService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class MyUserServiceImpl implements MyUserService {

	@Autowired
	private MyUserRepository userRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public List<MyUser> getAll() {
		List<MyUser> users = this.userRepository.findAll();
		return users;
	}
	
	@Override
	public MyUser findByUsername(String username) {
		MyUser user = this.userRepository.findByUsername(username);
		return user;
	}

	@Override
	public MyUser register(MyUser user) {
		try {
			Role role = roleService.getByRoleType("ROLE_USER");
			
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole(role);
			user.setConfirmPassword(null);
			user.setCreatedAt(new Date());
			
			return this.userRepository.insert(user);
		} catch(Exception e) {
			throw new UsernameException("Username '" + user.getUsername() + "' already exists");
		}
	}
	
	@Override
	public MyUser insertAdmin(MyUser user) {
		try {
			Role role = roleService.getByRoleType("ROLE_ADMIN");
			
			user.setPassword(encoder.encode(user.getPassword()));
			user.setRole(role);
			user.setConfirmPassword(null);
			user.setCreatedAt(new Date());
			
			return this.userRepository.insert(user);
		} catch(Exception e) {
			throw new UsernameException("Username '" + user.getUsername() + "' already exists");
		}
	}
	
	@Override
	public MyUser update(MyUser user) {
		return this.userRepository.save(user);
	}

	@Override
	public void delete(String userId) {
		this.userRepository.deleteById(userId);
	}

}
