package pl.bgolc.tachograph.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.data.service.DataResolver;
import pl.bgolc.tachograph.data.service.DataService;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.driver.Driver;
import pl.bgolc.tachograph.driver.DriverService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("driver")
public class DataController {

    private Logger log = LoggerFactory.getLogger(DataController.class);

    private DataService dataService;
    private DriverService driverService;
    private UserCredentials userCredentials;
    private DataResolver dataResolver;

    /*
    * Constructor
    * */
    @Autowired
    public DataController(DataService dataService, DriverService driverService, UserCredentials userCredentials, DataResolver dataResolver) {
        this.dataService = dataService;
        this.driverService = driverService;
        this.userCredentials = userCredentials;
        this.dataResolver = dataResolver;
    }

    @ModelAttribute("driver")
    public Driver driver() {
        return new Driver();
    }

    /*
    * New data
    * */
    @GetMapping("/newdata")
    public String newData(Model model, @ModelAttribute("driver") Driver driverP,
                          @RequestParam(name = "attached", defaultValue = "false") boolean fileattached, @RequestParam(name = "drivernotadded", defaultValue = "false") boolean driveradded) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));
        model.addAttribute("attached", fileattached);
        model.addAttribute("drivernotadded", driveradded);

        return "data/newdata";
    }

    @PostMapping(value = "/newdata", params = "alldata")
    public String newDataSubmit(@ModelAttribute("driver") Driver driver, RedirectAttributes redirectAttributes, @RequestParam("file")MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) {
            return "redirect:/newdata?attached=true";
        }
        if (driver.getDriverId() == null) {
            return "redirect:/newdata?drivernotadded=true";
        }

        dataResolver.downloadDataFromFile(driver.getDriverId(), multipartFile);
        dataService.saveAll(dataResolver.getDataList());
        redirectAttributes.addFlashAttribute("driver", driver);

        return "redirect:/displaydata?added=true";
    }

    @PostMapping(value = "/newdata", params = "dailydata")
    public String newDataSubmit2(@ModelAttribute("driver") Driver driver, RedirectAttributes redirectAttributes, @RequestParam("file")MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) {
            return "redirect:/newdata?attached=true";
        }
        if (driver.getDriverId() == null) {
            return "redirect:/newdata?drivernotadded=true";
        }

        dataResolver.downloadDataFromFile(driver.getDriverId(), multipartFile);
        dataService.saveAll(dataResolver.getDataList());
        redirectAttributes.addFlashAttribute("driver", driver);

        return "redirect:/displaydailydata?added=true";
    }

    /*
    * Previous data
    * */
    @GetMapping("/previousdata")
    public String prevData(Model model, @ModelAttribute("driver") Driver driver, @RequestParam(name = "drivernotadded", defaultValue = "false") boolean driveradded) {

        model.addAttribute("drivernotadded", driveradded);
        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "data/previousdata";
    }

    @PostMapping(value = "/previousdata", params = "alldata")
    public String prevDataSubmit(@ModelAttribute("driver") Driver driver, RedirectAttributes redirectAttributes) {

        if (driver.getDriverId() == null) {
            return "redirect:/previousdata?drivernotadded=true";
        }

        redirectAttributes.addFlashAttribute("driver", driver);

        return "redirect:/displaydata";
    }

    @PostMapping(value = "/previousdata", params = "dailydata")
    public String prevDataSubmit2(@ModelAttribute("driver") Driver driver, RedirectAttributes redirectAttributes) {

        if (driver.getDriverId() == null) {
            return "redirect:/previousdata?drivernotadded=true";
        }

        redirectAttributes.addFlashAttribute("driver", driver);

        return "redirect:/displaydailydata";
    }



}
