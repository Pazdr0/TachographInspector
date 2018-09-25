package pl.bgolc.tachograph.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.bgolc.tachograph.data.DataResolver;
import pl.bgolc.tachograph.model.User;
import pl.bgolc.tachograph.service.AuthenticationFacadeService;

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

        DataResolver resolver = new DataResolver();
        resolver.readData();

        return "home";
    }

}
