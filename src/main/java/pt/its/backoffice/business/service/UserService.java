package pt.its.backoffice.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pt.its.backoffice.business.dto.UserDTO;
import pt.its.backoffice.business.dto.UserFilterDTO;
import pt.its.backoffice.business.model.User;
import pt.its.backoffice.business.repository.UserRepository;
import pt.its.backoffice.core.exception.builder.MultiBusinessExceptionBuilder;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserDTO find(Long id) {
		UserDTO user = userRepository.find(id);
		return user;
	}
	
	public List<UserDTO> findAllUsers() {
		List<UserDTO> user = userRepository.findAllUsers();
		return user;
	}
	
	public List<UserDTO> find(UserFilterDTO filter) {
		return userRepository.find(filter); 
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	public UserDTO save(UserDTO userDTO) {
		validate(userDTO);
		User user = User.of(userDTO);
		user = userRepository.save(user);
		return userDTO;
	}
	
	private void validate(UserDTO user) {
		MultiBusinessExceptionBuilder.instance()
				.addRequiredException("username", user.getUsername())
				.addRequiredException("password", user.getPassword())
				.addRequiredException("name", user.getName())
				.addException("Já existe um utilizador cadastrado com o username informado!", validateDuplicate(user))
				.handleThrowException();
	}

	private boolean validateDuplicate(UserDTO userDTO) {
		User user = userRepository.findByUsername(userDTO.getUsername());
		return (userDTO.getId() == null && user != null) || (user != null && !user.getId().equals(userDTO.getId()));
	}
}
