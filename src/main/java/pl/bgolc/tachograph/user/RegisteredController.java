package pl.bgolc.tachograph.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registered")
public class RegisteredController {

    @GetMapping
    public String registered() {
        return "user/registered";
    }
}
