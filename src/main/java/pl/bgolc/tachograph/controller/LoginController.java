package pl.bgolc.tachograph.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.service.UserService;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

	@Autowired
	private UserService userService;
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping
	public String login() {
		return "login";
	}
	
	@PostMapping
	public String loggedIn() {
		Boolean loggedIn = userService.login("asda", "tachograf");
		if (loggedIn == true) {
			log.info("Zalogowano użytkownika: " + "tachograf");
		} else {
			log.info("Błąd logowania");
		}
		
		userService.getUsers();
		
		return "redirect:/home";
	}
}
