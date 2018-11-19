package pl.bgolc.tachograph.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.bgolc.tachograph.data.inspection.Inspector;
import pl.bgolc.tachograph.data.model.Data;

import java.util.List;

@Controller
@SessionAttributes("datalist")
public class DataValidationController {

    private Logger log = LoggerFactory.getLogger(DataValidationController.class);

    @GetMapping("/validatedata")
    public String getValidation(@ModelAttribute("datalist") List<Data> dataList) {

        Inspector inspector = Inspector.getInstance();
        inspector.setData(dataList);


        return "data/validatedata";
    }
}
