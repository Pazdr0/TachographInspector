package pl.bgolc.tachograph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DataController {

    @GetMapping("/newdata")
    public String newData() {
        return "newdata";
    }

    @GetMapping("/previousdata")
    public String prevData() {
        return "previousdata";
    }
}
