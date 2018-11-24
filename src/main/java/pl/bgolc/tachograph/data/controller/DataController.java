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
@SessionAttributes({"driver", "datalist"})
public class DataController {

    /*
    * Components
    * */
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

    @ModelAttribute("datalist")
    public List<Data> dataList() {
        return new ArrayList<Data>();
    }

    /*
    * Model
    * */
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

    @PostMapping("/newdata")
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

    /*
    * Previous data
    * */
    @GetMapping("/previousdata")
    public String prevData(Model model, @ModelAttribute("driver") Driver driverP) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "data/previousdata";
    }

    @PostMapping("/previousdata")
    public String prevDataSubmit(@ModelAttribute("driver") Driver driverP, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("driver", driverP);

        return "redirect:/displaydata";
    }

    /*
    * Display data
    * */
    @GetMapping("/displaydata")
    public String displayData(Model model, @ModelAttribute("driver") Driver driver, @ModelAttribute("since") String since, @ModelAttribute("to") String to,
                              RedirectAttributes redirectAttributes, @RequestParam(value = "added", defaultValue = "false") boolean added) {

        model.addAttribute("added", added);

        if (driver.getDriverId() != null) {
            if (!since.isEmpty() && !to.isEmpty()) {
                log.debug("Od: " + since + ", do: " + to);
                model.addAttribute("datalist", dataService.findDataSinceTo(since, to, driver.getDriverId()));
            } else if (!since.isEmpty() && to.isEmpty()) {
                String now = LocalDate.now().toString();
                log.debug("Od: " + since + ", do: " + now);
                model.addAttribute("datalist", dataService.findDataSinceTo(since, now, driver.getDriverId()));
            } else if (since.isEmpty() && !to.isEmpty()) {
                log.debug("Od: 1990-01-01" + ", do: " + to);
                model.addAttribute("datalist", dataService.findDataSinceTo("1990-01-01", to, driver.getDriverId()));
            } else {
                List<Data> dataList = dataService.findByDriverId(driver.getDriverId());
                model.addAttribute("datalist", dataList);
                redirectAttributes.addFlashAttribute("datalist", dataList);
            }
        } else {
            log.error("There was no driver selected");
        }

        return "data/displaydata";
    }

    @PostMapping(value="/displaydata", params="choosedate")
    public String chooseDate(@ModelAttribute("since") String since, @ModelAttribute("to") String to, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("since", since);
        redirectAttributes.addFlashAttribute("to", to);

        return "redirect:/displaydata";
    }

    @PostMapping(value="/displaydata", params="validate")
    public String validateDate() {

        return "redirect:/validatedata";
    }
}
