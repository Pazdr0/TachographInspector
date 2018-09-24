package pl.bgolc.tachograph.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.model.User;
import pl.bgolc.tachograph.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping({"/login"})
public class LoginController {
	
	@GetMapping
	public String login() {
		return "login";
	}

//	@PostMapping
//	public String logged() {
//		return "redirect:/register";
//	}
}
