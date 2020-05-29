package pt.its.backoffice.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pt.its.backoffice.core.security.AuthenticationFilter;
import pt.its.backoffice.core.security.LoginFilter;
import pt.its.backoffice.core.security.UserDetailsServiceImpl;




@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String LOGIN_ENDPOINT = "/login";
	private static final String[] SWAGGER_ENDPOINTS = { "/v2/api-docs", "/webjars/**", "/swagger-resources/**",	"/configuration/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js" };
	public static final List<String> PERMITTED_ENDPOINTS = Arrays.asList(LOGIN_ENDPOINT);
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.POST, LOGIN_ENDPOINT).permitAll()
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new LoginFilter(LOGIN_ENDPOINT, authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new AuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
	}
	

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(SWAGGER_ENDPOINTS);
    }

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}
	
}