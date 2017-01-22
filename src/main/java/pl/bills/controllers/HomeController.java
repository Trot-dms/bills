package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bills.services.BillsService;

/**
 * Created by trot on 09.01.17.
 */

@Controller
public class HomeController {

    @Autowired
    BillsService billsService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("activeMenu", "home");
        model.addAttribute("total", billsService.totalBillsPrice());
        model.addAttribute("biggest", billsService.biggestBillPrice());
        model.addAttribute("frequent", billsService.mostFrequentBill());
        return "home";
    }
}
