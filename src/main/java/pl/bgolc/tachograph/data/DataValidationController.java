package pl.bgolc.tachograph.data;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataValidationController {

    @GetMapping("/validatedata")
    public String getValidation() {
        return "data/validatedata";
    }
}
