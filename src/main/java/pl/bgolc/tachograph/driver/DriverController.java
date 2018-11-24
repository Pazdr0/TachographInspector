package pl.bgolc.tachograph.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.data.controller.DataController;

import javax.validation.Valid;

@Controller
public class DriverController {

    /*
    * Fields
    * */
    private Logger log = LoggerFactory.getLogger(DataController.class);

    private UserCredentials userCredentials;
    private DriverService driverService;
    private Boolean added;
    /*
    * Constructor
    * */
    @Autowired
    public DriverController(UserCredentials userCredentials, DriverService driverService) {
        this.userCredentials = userCredentials;
        this.driverService = driverService;
    }

    /*
    * Model new driver
    * */
    @GetMapping("/newdriver")
    public String newDriver(@ModelAttribute Driver driver, Model model, @RequestParam(name = "added", defaultValue = "false") boolean added) {

        log.debug("Value of added: " + added);
        model.addAttribute("added", added);

        return "driver/newdriver";
    }

    @PostMapping("/newdriver")
    public String addDriver(@Valid @ModelAttribute("driver") Driver driver, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            log.error("Erros in newdriver form");

            return "driver/newdriver";
        } else {

            log.debug("Saving driver...");
            driver.setUserId(userCredentials.getUserId());
            driverService.save(driver);
            log.debug("Driver: " + driver.getFirstName() + " " + driver.getSurname() + " has been saved");

            return "redirect:/newdriver?added=true";
        }
    }


    /*
    * Model existing drives
    * */
    @GetMapping("/existingdrivers")
    public String existingDrivers(Model model) {
        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));
        return "driver/existingdrivers";
    }

}

