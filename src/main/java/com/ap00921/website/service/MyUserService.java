/**
 * UsersService.java
 */
package com.ap00921.website.service;

import java.util.List;

import com.ap00921.website.model.user.MyUser;

/**
 * @author Alex Daniel Popa
 *
 */
public interface MyUserService {

	public List<MyUser> getAll();

	public MyUser findByUsername(String username);

	public MyUser register(MyUser user);
	
	public MyUser insertAdmin(MyUser user);

	public MyUser update(MyUser user);

	public void delete(String userId);

}
