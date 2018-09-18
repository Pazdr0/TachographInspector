package pl.bgolc.tachograph.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.model.User;
import pl.bgolc.tachograph.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public String showRegisterForm() {
		return "register";
	}
	
	@PostMapping
	public String register(@ModelAttribute("user") User user, BindingResult errors) {

		String username = user.getUserName();
		String passwd = user.getPassword();
		String email = user.getEmail();
		
		User newUser = new User(username, email, passwd);
		
		userService.register(newUser.getUserName(), newUser.getEmail(), newUser.getPassword());
		//		userService.register(user.getUserName(), user.getEmail(), user.getPassword());
		return "register";
	}
}
