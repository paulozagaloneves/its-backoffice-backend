package pt.its.backoffice.core.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationFilter extends GenericFilterBean {
	
	private static final List<String> PERMITTED_ENDPOINTS = Arrays.asList("login", "/change-password/update-password", "recovery-mail");
	
	
	@Value("${server.servlet.context-path}")
	private String contextPath;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = ((HttpServletRequest) request).getHeader(SecurityConstants.AUTHORIZATION_HEADER_KEY);

		Authentication authentication = JwtTokenHelper.getAuthentication(token);
		if (authentication == null && !isPermittedEndpoint(request)) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
		} else if(authentication != null) {
			token = JwtTokenHelper.generateToken(authentication);
			((HttpServletResponse) response).addHeader(SecurityConstants.AUTHORIZATION_HEADER_KEY, SecurityConstants.TOKEN_PREFIX + " " + token);
			((HttpServletResponse) response).addHeader("Access-Control-Expose-Headers", SecurityConstants.AUTHORIZATION_HEADER_KEY);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}
	
	private boolean isPermittedEndpoint(ServletRequest request) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		return PERMITTED_ENDPOINTS.stream().anyMatch(p -> httpRequest.getRequestURI().contains(p));
	}

}