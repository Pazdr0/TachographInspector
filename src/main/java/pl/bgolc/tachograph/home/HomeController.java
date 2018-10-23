package pl.bgolc.tachograph.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.authentication.AuthenticationFacadeService;
import pl.bgolc.tachograph.user.User;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    @GetMapping
    public String home() {

        User user = new User();
        Authentication authentication = authenticationFacadeService.getAuthentication();
        user.setUserName(authentication.getName());
        log.info("Logged in as: " + user.getUserName());

        return "home";
    }

}
