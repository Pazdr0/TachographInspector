package pl.bgolc.tachograph.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import pl.bgolc.tachograph.user.verification.VerificationToken;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class RegistrationController {

    private Logger log = LoggerFactory.getLogger(RegistrationController.class);

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/control")
    public String control() {
        return "redirect:/register";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(WebRequest webRequest, Model model, @RequestParam("token") String token) {

        Locale locale = webRequest.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            log.error("Wrong token");
            return "redirect:/register";
        }

        log.debug("Found token: " + token);

        LocalDateTime now = LocalDateTime.now();

        if (!now.isBefore(verificationToken.getLocalExpiryDate())) {
            log.error("Verification email expired");
            return "redirect:/register";
        }

        userService.enableRegisteredUser(userService.findById(verificationToken.getUserId()));

        log.info("User has been enabled");

        return "redirect:/login";
    }

}
