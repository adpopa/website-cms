/**
 * SecurityConstants.java
 */
package com.ap00921.website.security;

/**
 * @author Alex Daniel Popa
 *
 */
public class SecurityConstants {

	public final static String ADMIN_URLS = "/api/admin/**";
	public final static String USER_URLS = "/api/user/**";
	public final static String GUEST_URLS = "/api/products/**";
	
	public final static String SECRET_KEY = "SecretKeyToGenerateJWTs";
	public final static String TOKEN_PREFIX = "Bearer ";
	public final static String HEADER_STRING = "Authorization";
	public final static long EXPIRATION_TIME = 3_600_000; // 1 sec = 1_000 ms | 3_600_000 = 1hr

	public final static String ENCRYPTION_PASSWORD = "f8dfa82eb4f97ab8";
	public final static String ENCRYPTION_SALT = "40cd4ef097411c5c";
}
