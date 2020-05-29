package pt.its.backoffice.business.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.its.backoffice.business.dto.UserDTO;
import pt.its.backoffice.business.dto.UserFilterDTO;
import pt.its.backoffice.business.service.UserService;


@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public UserDTO find(@PathVariable Long id) {
		return userService.find(id);
	}
	
	@PostMapping
	public UserDTO save(@RequestBody UserDTO user) {
		return userService.save(user);
	}

	@PutMapping
	public UserDTO update(@RequestBody UserDTO user) {
		return userService.save(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@GetMapping
	public List<UserDTO> find(UserFilterDTO filter) {
		return userService.find(filter);
	}
	
}
