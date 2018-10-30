package pl.bgolc.tachograph.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.user.User;

@Controller
@RequestMapping({"/", "/home"})
public class HomeController {

    /*
    * Components
    * */
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    private UserCredentials userCredentials;

    /*
    * Constructor
    * */
    @Autowired
    public HomeController(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    /*
    * Methods
    * */
    @GetMapping
    public String home() {

        log.info("Logged in as: " + userCredentials.getName());

        return "home";
    }

}
