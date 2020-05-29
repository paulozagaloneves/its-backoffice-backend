package pt.its.backoffice.core.config;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import pt.its.backoffice.core.security.SecurityConstants;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String SWAGGER_VERSION = "0.0.1";
	private static final String SWAGGER_DESCRIPTION = "IT Sector Exame - ITS Backoffice";
	private static final String SWAGGER_TITLE = "REST API - ITS Backoffice";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
						.withClassAnnotation(RestController.class))
						.paths(PathSelectors.any())
						.build().apiInfo(apiInfo()).securitySchemes(Arrays.asList(apiKey()))
						.securityContexts(Collections.singletonList(securityContext()));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
	}

	private List<SecurityReference> defaultAuth() {
		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[] { authorizationScope };
		return Collections.singletonList(new SecurityReference(SecurityConstants.TOKEN_PREFIX, authorizationScopes));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(SWAGGER_TITLE)
				.description(SWAGGER_DESCRIPTION)
				.termsOfServiceUrl("")
				.version(SWAGGER_VERSION)
				.build();
	}

	private ApiKey apiKey() {
		return new ApiKey(SecurityConstants.TOKEN_PREFIX, SecurityConstants.AUTHORIZATION_HEADER_KEY, "header");
	}
}