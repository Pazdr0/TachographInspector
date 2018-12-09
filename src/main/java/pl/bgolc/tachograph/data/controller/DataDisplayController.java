package pl.bgolc.tachograph.data.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.bgolc.tachograph.data.model.Data;
import pl.bgolc.tachograph.data.service.DataInspector;
import pl.bgolc.tachograph.data.service.DataService;
import pl.bgolc.tachograph.data.service.DayService;
import pl.bgolc.tachograph.driver.Driver;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes({"driver", "datalist"})
public class DataDisplayController {

    private Logger log = LoggerFactory.getLogger(DataDisplayController.class);

    private DataService dataService;
    private DayService dayService;
    private DataInspector dataInspector;

    @Autowired
    public DataDisplayController(DataService dataService, DayService dayService, DataInspector dataInspector) {
        this.dataService = dataService;
        this.dayService = dayService;
        this.dataInspector = dataInspector;
    }

    @ModelAttribute("driver")
    public Driver driver() {
        return new Driver();
    }

    /*
     * Display all data
     * */
    @GetMapping("/displaydata")
    public String displayData(@ModelAttribute("driver") Driver driver, @ModelAttribute("since") String since, @ModelAttribute("to") String to,
                               @RequestParam(value = "added", defaultValue = "false") boolean added, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("added", added);

        if (driver.getDriverId() != null) {
            if (!since.isEmpty() && !to.isEmpty()) {
                model.addAttribute("datalist", dataService.findDataSinceTo(since, to, driver.getDriverId()));
            } else if (!since.isEmpty() && to.isEmpty()) {
                String now = LocalDate.now().toString();
                model.addAttribute("datalist", dataService.findDataSinceTo(since, now, driver.getDriverId()));
            } else if (since.isEmpty() && !to.isEmpty()) {
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

    /*
    * Display daily data
    * */
    @GetMapping("/displaydailydata")
    public String displayDailyData(@ModelAttribute("driver") Driver driver, @ModelAttribute("since") String since, @ModelAttribute("to") String to,
                                   @RequestParam(value = "added", defaultValue = "false") boolean added, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("added", added);

        dayService.getDayList(driver.getDriverId());

        if (driver.getDriverId() == null) {
            log.error("There was no driver selected");
        } else {
            if (!since.isEmpty() && !to.isEmpty()) {
                model.addAttribute("daylist", dataInspector.checkDays(dayService.getDayList(since, to, driver.getDriverId())));
            } else if (!since.isEmpty() && to.isEmpty()) {
                String now = LocalDate.now().toString();
                model.addAttribute("daylist", dataInspector.checkDays(dayService.getDayList(since, now, driver.getDriverId())));
            } else if (since.isEmpty() && !to.isEmpty()) {
                model.addAttribute("daylist", dataInspector.checkDays(dayService.getDayList("1990-01-01", to, driver.getDriverId())));
            } else {
                model.addAttribute("daylist", dataInspector.checkDays(dayService.getDayList(driver.getDriverId())));
            }
        }

        return "data/displaydailydata";
    }

    @PostMapping(value="/displaydailydata", params="choosedate")
    public String chooseDailyDate(@ModelAttribute("since") String since, @ModelAttribute("to") String to, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("since", since);
        redirectAttributes.addFlashAttribute("to", to);

        return "redirect:/displaydailydata";
    }
}
