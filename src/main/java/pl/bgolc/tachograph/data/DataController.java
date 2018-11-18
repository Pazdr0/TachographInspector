package pl.bgolc.tachograph.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bgolc.tachograph.authentication.UserCredentials;
import pl.bgolc.tachograph.driver.Driver;
import pl.bgolc.tachograph.driver.DriverService;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
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

    /*
    * Model
    * */
    /*
    * New data
    * */
    @GetMapping("/newdata")
    public String newData(Model model, @ModelAttribute("driverP") Driver driverP) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "data/newdata";
    }

    @PostMapping("/newdata")
    public String newDataSubmit(@ModelAttribute("driverP") Driver driverP, RedirectAttributes redirectAttributes, @RequestParam("file")MultipartFile multipartFile) {

        dataResolver.downloadDataFromFile(driverP.getDriverId(), multipartFile);
        dataService.saveAll(dataResolver.getDataList());

        redirectAttributes.addFlashAttribute("driverP", driverP);

        //TODO na jutro zaimplementowac mozliwosc wybierania dat na formatce displaydata i wyszukiwac dane z bazy od daty do daty
        //TODO dodatkowo 2 buttony i jeden do wybierania dat, a drugi do inspekcji danych i zaczac implementowac inspekcje danych
        //TODO zaimplementowac jakis error handling lepszy

        return "redirect:/displaydata";
    }

    /*
    * Previous data
    * */
    @GetMapping("/previousdata")
    public String prevData(Model model, @ModelAttribute("driverP") Driver driverP) {

        model.addAttribute("drivers", driverService.findByUserId(userCredentials.getUserId()));

        return "data/previousdata";
    }

    @PostMapping("/previousdata")
    public String prevDataSubmit(@ModelAttribute("driverP") Driver driverP, RedirectAttributes redirectAttributes, HttpSession session) {

        redirectAttributes.addFlashAttribute("driverP", driverP);
        session.setAttribute("driver", driverP);

        return "redirect:/displaydata";
    }

    /*
    * Display data
    * */
    @GetMapping("/displaydata")
    public String displayData(Model model, /*@ModelAttribute("datalist") List<Data> dataList*/@ModelAttribute("driverP") Driver driver, @ModelAttribute("since") String since, @ModelAttribute("to") String to) {

        if (driver.getDriverId() != null) {
            model.addAttribute("datalist", dataService.findByDriverId(driver.getDriverId()));
        } else {
            log.error("There was no driver selected");
        }

        return "data/displaydata";
    }

    @PostMapping(value="/displaydata", params="choosedate")
    public String chooseDate(/*@SessionAttribute("driver") Driver driver*/ HttpSession session, Model model, @ModelAttribute("since") String since, @ModelAttribute("to") String to, RedirectAttributes redirectAttributes) {
        Driver driver = (Driver)session.getAttribute("driver");
        log.info("Od: " + since + ", Do: " + to);
        log.info("kierowca: " + driver.getDriverId());
        if (driver.getDriverId() != null) {
            model.addAttribute("datalist", dataService.findByDriverId(driver.getDriverId()));
        }
        redirectAttributes.addFlashAttribute("driverP", driver);

        return "redirect:/displaydata";
    }

    @PostMapping(value="/displaydata", params="validate")
    public String validateDate() {

        return "redirect:/validatedata";
    }
}
