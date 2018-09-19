package pl.bgolc.tachograph.controller;

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
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
	public String showRegisterForm(@ModelAttribute User user) {
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("password", user.getPassword());

		userService.register(user.getUserName(), user.getEmail(), user.getPassword());

		return "redirect:/registered";
	}

	@GetMapping("/registered")
	public String registered() {
		return "registered";
	}
}
