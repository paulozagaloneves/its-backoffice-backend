package pt.its.backoffice.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import pt.its.backoffice.business.model.User;


public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -1578502664056492987L;
	private String username;
	private String password;

	public UserDetailsImpl() {
	}

	public UserDetailsImpl(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}