/**
 * MyUserDetailsService.java
 */
package com.ap00921.website.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
public interface MyUserDetailsService extends UserDetailsService {

	public MyUser loadUserbyUserId(String userId);
}
