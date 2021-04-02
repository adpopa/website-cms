package com.ap00921.website.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.repository.MyUserRepository;
import com.ap00921.website.service.MyUserDetailsService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

	@Autowired
	private MyUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRoleType()));

		System.out.println(authorities);
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

	@Transactional
	@Override
	public MyUser loadUserbyUserId(String userId) {
		MyUser user = userRepository.getByUserId(userId);
		
		if (user == null) 
			throw new UsernameNotFoundException("User not found");
		
		return user;
	}
}
