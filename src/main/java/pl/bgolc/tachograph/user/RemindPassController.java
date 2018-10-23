package pl.bgolc.tachograph.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/remindpass")
public class RemindPassController {

    @GetMapping
    public String remind() {
        return "remindpass";
    }
}
