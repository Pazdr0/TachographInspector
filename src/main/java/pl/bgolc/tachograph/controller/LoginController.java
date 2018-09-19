package pl.bgolc.tachograph.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.model.User;
import pl.bgolc.tachograph.service.UserService;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

	@Autowired
	private UserService userService;
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping
	public String login(@ModelAttribute User user) {
		return "login";
	}
	
	@PostMapping
	public String loggedIn(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("password", user.getPassword());

		Boolean loggedIn = userService.login(user.getUserName(), user.getPassword());
		if (loggedIn == true) {
			log.info("Zalogowano użytkownika: " + user.getUserName());
			return "redirect:/home";
		} else {
			log.info("Błąd logowania");
			return "login";
		}
	}
}
