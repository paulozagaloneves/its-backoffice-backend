package pt.its.backoffice.business.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pt.its.backoffice.business.dto.AuthenticationDTO;

@RestController
public class LoginController {

	@PostMapping("/login")
	public String login(@RequestBody AuthenticationDTO authentication) {
		// TODO Método criado apenas para aparecer no Swagger. Ver outra forma para
		// exibir.
		return "redirect:/login";
	}

}
