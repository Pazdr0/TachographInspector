package pl.bgolc.tachograph.data;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bgolc.tachograph.authentication.AuthenticationFacadeService;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.driver.Driver;
import pl.bgolc.tachograph.driver.DriverService;
import pl.bgolc.tachograph.user.User;

@Controller
public class DataController {

    /*
    * Components
    * */
    private Logger log = LoggerFactory.getLogger(DataController.class);

    private DataService dataService;
    private DriverService driverService;
    private UserCredentials userCredentials;

    /*
    * Constructor
    * */
    @Autowired
    public DataController(DataService dataService, DriverService driverService, UserCredentials userCredentials) {
        this.dataService = dataService;
        this.driverService = driverService;
        this.userCredentials = userCredentials;
    }

    /*
    * Model
    * */
    @GetMapping("/newdata")
    public String newData(@ModelAttribute Driver driverP) {
    	return "data/newdata";
    }

    @PostMapping("/newdata")
    public String newDataSubmit(@ModelAttribute Driver driverP, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("driverP", driverP);
        
        return "data/displaydata";
    }

    @GetMapping("/previousdata")
    public String prevData(Model model, @ModelAttribute("driverP") Driver driverP) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "data/previousdata";
    }

    @PostMapping("/previousdata")
    public String prevDataSubmit(@ModelAttribute("driverP") Driver driverP, RedirectAttributes redirectAttributes) {

//        log.info("Id: " + driverP.getDriverId());
        redirectAttributes.addFlashAttribute("driverP", driverP);

        return "redirect:/displaydata";
    }

    @GetMapping("/displaydata")
    public String displayData(Model model, @ModelAttribute("driverP") Driver driverP) {

        if (driverP.getDriverId() != null) {
            model.addAttribute("datalist", dataService.findByDriverId(driverP.getDriverId()));
        } else {
            log.error("There was no driver selected");
        }

        return "data/displaydata";
    }
}
