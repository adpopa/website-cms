/**
 * JwtTokenProvider.java
 */
package com.ap00921.website.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ap00921.website.service.MyUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import static com.ap00921.website.security.SecurityConstants.EXPIRATION_TIME;
import static com.ap00921.website.security.SecurityConstants.SECRET_KEY;

/**
 * @author Alex Daniel Popa
 *
 */
@Component
public class JwtTokenProvider {
	
	@Autowired
	public MyUserService userService;;

	// Generate the token
	public String generateToken(Authentication auth) {
		
		Date now = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
		
		String username = auth.getName();
		String authority = String.valueOf(auth.getAuthorities().toArray()[0]);
		String userId = userService.findByUsername(username).getUserId();

		Map<String, Object> claims = new HashMap<>();
		
		claims.put("id", userId);
		claims.put("username", username);
		claims.put("authorities", authority);
		
		String jwt = Jwts.builder()
				.setSubject(userId)
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
		
		return jwt;
	}

	// Validate the token
	public boolean validateToken(String token) {
		System.out.println("intra");
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("invalid jwt signature");
		} catch (MalformedJwtException e) {
			System.out.println("invalid jwt token");
		} catch (ExpiredJwtException e) {
			System.out.println("expired jwt token");
		} catch (UnsupportedJwtException e) {
			System.out.println("unsupported jwt token");
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		return false;
	}
	
	// Get user Id from token
	public String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		String id = (String) claims.get("id");
		
		return id;
	}
	
	// Get roles from token
	public Collection<? extends GrantedAuthority> getRolesFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		SimpleGrantedAuthority usertype = new SimpleGrantedAuthority((String) claims.get("authorities"));
		return Arrays.asList(usertype);
	}

}
