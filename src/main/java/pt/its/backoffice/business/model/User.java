package pt.its.backoffice.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import pt.its.backoffice.business.dto.UserDTO;

@Entity
public class User extends EntityBase {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, unique = true, length = 50)
	private String username;

	@Column(nullable = false, length = 100)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public static User of(UserDTO userDTO) {
		User user = map(userDTO, User.class);
		return user;
	}

}
