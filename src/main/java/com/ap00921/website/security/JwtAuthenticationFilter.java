/**
 * JwtAuthenticationFilter.java
 */
package com.ap00921.website.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ap00921.website.model.user.MyUser;
import com.ap00921.website.service.MyUserDetailsService;

import static com.ap00921.website.security.SecurityConstants.HEADER_STRING;
import static com.ap00921.website.security.SecurityConstants.TOKEN_PREFIX;

/**
 * @author Alex Daniel Popa
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			String jwt = getJWTFromRequest(request);
			
			if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				
				String userId = tokenProvider.getUserIdFromJWT(jwt);
				MyUser userDetails = userDetailsService.loadUserbyUserId(userId);
				System.out.println(userDetails);
				Collection<? extends GrantedAuthority> userType = tokenProvider.getRolesFromJWT(jwt);
				
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userType);
				
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			}
		} catch (Exception e){
			logger.error("Could not set user auth in security context", e);
		}
		
		filterChain.doFilter(request, response);
	}

	private String getJWTFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(HEADER_STRING);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(7, bearerToken.length()); // Return without TOKEN_PREFIX
		}

		return null;
	}
}
