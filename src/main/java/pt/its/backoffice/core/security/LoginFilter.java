package pt.its.backoffice.core.security;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	
	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		UserDetailsImpl userDetails = new ObjectMapper()
				.readValue(request.getInputStream(), UserDetailsImpl.class);
		
		response.addHeader("Access-Control-Allow-Headers", "Accept, Content-Type, Access-Control-Allow-Headers, X-Requested-With, " + SecurityConstants.AUTHORIZATION_HEADER_KEY);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						userDetails.getUsername(), 
						userDetails.getPassword(), 
						userDetails.getAuthorities()
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, 
			HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		
	    response.addHeader("Access-Control-Expose-Headers", SecurityConstants.AUTHORIZATION_HEADER_KEY);
		String token = JwtTokenHelper.generateToken(auth);
		
		response.addHeader(SecurityConstants.AUTHORIZATION_HEADER_KEY, SecurityConstants.TOKEN_PREFIX + " " + token);
		

	}
}