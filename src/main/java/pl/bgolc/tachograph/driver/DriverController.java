package pl.bgolc.tachograph.driver;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.data.DataController;

@Controller
public class DriverController {

    /*
    * Fields
    * */
    private Logger log = LoggerFactory.getLogger(DataController.class);

    private UserCredentials userCredentials;
    private DriverService driverService;
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
    public String newDriver(@ModelAttribute Driver driver) {
        return "newdriver";
    }

    //TODO dodac jakas wiadomosc w stylu alert, ze dodano kierowce
    @PostMapping("/newdriver")
    public String addDriver(@ModelAttribute("driver") Driver driver, BindingResult bindingResult, Model model) {
        driver.setUserId(userCredentials.getUserId());
//        model.addAttribute("firstName", driver.getFirstName());
//        model.addAttribute("surname", driver.getSurname());
        log.info("name: " + driver.getFirstName());
        driverService.save(driver);
        return "newdriver";
    }


    /*
    * Model existing drives
    * */
    @GetMapping("/existingdrivers")
    public String existingDrivers(Model model) {
        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));
        return "existingdrivers";
    }
}
