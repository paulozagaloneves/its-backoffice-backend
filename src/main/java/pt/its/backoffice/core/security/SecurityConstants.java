package pt.its.backoffice.core.security;

public final class SecurityConstants {
	
	private SecurityConstants() {}

	
	// EXPIRATION_TIME = 1 h
	public static final long EXPIRATION_TIME = 3_600_000;
	public static final String SECRET = "SecretProject";
	public static final String AUTHORITIES_KEY = "authorities";
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";

}
