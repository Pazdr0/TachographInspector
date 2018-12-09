package pl.bgolc.tachograph.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import pl.bgolc.tachograph.user.verification.OnRegistrationCompleteEvent;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private UserService userService;
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public RegisterController(UserService userService, ApplicationEventPublisher applicationEventPublisher) {
        this.userService = userService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @GetMapping
    public String showRegisterForm(@ModelAttribute User user) {
        return "user/register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, WebRequest webRequest) {

        model.addAttribute("userName", user.getUserName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("confirmPassword", user.getConfirmPassword());

        if (bindingResult.hasErrors()) {

            log.debug("Errors in register form");

            return "user/register";
        } else {
            if (userService.checkIfEmailExists(user.getEmail()) || userService.checkIfUserNameExists(user.getUserName())) {

                bindingResult.reject("usertaken", "Podany adres email, bądz użytkownik jest już zajety");
                log.error("Either username or email is takes");

                return "user/register";
            } else {
              try {
                    userService.register(user.getUserName(), user.getEmail(), user.getPassword());
                    String appUrl = webRequest.getContextPath();
                    User registered = userService.findByUserName(user.getUserName());
                    applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, webRequest.getLocale(), appUrl));

                } catch (Exception ex) {

                    bindingResult.reject("errorsending", "Wystapil blad podczas wysylania wiadomosci");
                    log.error("Sending email has occurred an error: " + ex.getMessage());

                    return "user/register";
                }
                log.info("Registration success, verification mail will be send by email");

                return "redirect:/registered";
            }
        }
    }

}
