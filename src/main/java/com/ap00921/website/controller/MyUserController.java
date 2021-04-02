/**
 * UserController.java
 */
package com.ap00921.website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.payload.JWTLoginResponse;
import com.ap00921.website.payload.LoginRequest;
import com.ap00921.website.security.JwtTokenProvider;
import com.ap00921.website.service.MyUserService;
import com.ap00921.website.service.ValidationMapErrorService;
import com.ap00921.website.validator.MyUserValidator;

import static com.ap00921.website.security.SecurityConstants.TOKEN_PREFIX;

/**
 * @author Alex Daniel Popa
 *
 */
@RestController
@RequestMapping("/api/credentials")
@CrossOrigin
public class MyUserController {

	@Autowired
	private MyUserService userService;
	
	@Autowired
	private ValidationMapErrorService validationMapErrorService;
	
	@Autowired
	private MyUserValidator userValidator;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		
		ResponseEntity<?> error = validationMapErrorService.ValidationMapService(result);
		
		if (error != null) {
			return error;
		}
		
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()
				)
		);
		
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);
		
		return ResponseEntity.ok(new JWTLoginResponse(true, jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody MyUser user, BindingResult result) {
		userValidator.validate(user, result);
		
		ResponseEntity<?> error = validationMapErrorService.ValidationMapService(result);
		if (error != null)
			return error;
		
		MyUser newUser = userService.register(user);
		
		return new ResponseEntity<MyUser>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public void check() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
	}
}
