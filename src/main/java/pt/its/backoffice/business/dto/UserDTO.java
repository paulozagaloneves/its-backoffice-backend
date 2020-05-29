package pt.its.backoffice.business.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends EntityBaseDTO {

	private String name;

	private String username;

	private String password;

	private String confirmPassword;

	public UserDTO() {
	}
	
	public UserDTO(Long id, String name, String username, String password, Date creation, Date lastUpdated, boolean status, String creationUsername, String lastUpdatedUsername) {
		super(id, creation, lastUpdated, status, creationUsername, lastUpdatedUsername);
		this.name = name;
		this.username = username;
		this.password = password;
	}

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
