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
@RequestMapping("/register")
public class RegisterController {

    private final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public String showRegisterForm(@ModelAttribute User user) {
        return "register";
    }

    @PostMapping
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult, Model model) {

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("confirmPassword", user.getConfirmPass());

        if (!user.getPassword().equals(user.getConfirmPass())) {
            log.error("Błąd rejestracji" + user.getConfirmPass());
            return "register";
        } else {
            log.info("Rejestracja udana");
            userService.register(user.getUserName(), user.getEmail(), user.getPassword());
            return "redirect:/registered";
        }
    }

}
