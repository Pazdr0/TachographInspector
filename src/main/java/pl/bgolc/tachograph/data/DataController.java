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

import pl.bgolc.tachograph.authentication.AuthenticationFacadeService;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.driver.Driver;
import pl.bgolc.tachograph.driver.DriverService;
import pl.bgolc.tachograph.user.User;

@Controller
@RequestMapping
public class DataController {

    /*
    * Components
    * */
    private Logger log = LoggerFactory.getLogger(DataController.class);

    private DriverService driverService;
    private UserCredentials userCredentials;

    /*
    * Constructor
    * */
    @Autowired
    public DataController(DriverService driverService, UserCredentials userCredentials) {
        this.driverService = driverService;
        this.userCredentials = userCredentials;
    }

    /*
    * Model
    * */
    @GetMapping("/newdata")
    public String newData(Model model) {
        model.addAttribute("driver", new Driver());
    	return "newdata";
    }

    @PostMapping("/newdata")
    public String newDataSubmit(@ModelAttribute Driver driver) {

        return "newdata";
    }


    @GetMapping("/previousdata")
    public String prevData(Model model) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "previousdata";
    }

    @PostMapping("/previousdata")
    public String prevDataSubmit(@ModelAttribute("driverP") Driver driverP) {

//        log.info("Id: " + driverPost.getDriverId());

        return "previousdata";
    }
}
