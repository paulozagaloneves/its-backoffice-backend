package pt.its.backoffice.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	private static final List<String> ALLOWED_HEADERS = Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization");
	private static final List<String> ALLOWED_METHODS = Arrays.asList("POST", "OPTIONS", "GET", "DELETE", "PUT");

	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.setAllowedHeaders(ALLOWED_HEADERS);
	    config.setAllowedMethods(ALLOWED_METHODS);
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}