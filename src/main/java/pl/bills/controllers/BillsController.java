package pl.bills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.bills.forms.RecordForm;
import pl.bills.services.BillsService;
import pl.bills.services.StatusService;

/**
 * Created by trot on 19.01.17.
 */

@Controller
public class BillsController {

    @Autowired
    BillsService billsService;

    @Autowired
    StatusService statusService;

    @RequestMapping(value = "/bills", method = RequestMethod.GET)
    public ModelAndView bills(Model model) {
        model.addAttribute("activeMenu", "bills");
        ModelAndView mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.getBills());
        mav.addObject("form", new RecordForm());
        mav.addObject("statusList", statusService.getAllStatuses());
        return mav;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(ModelAndView mav, @RequestParam String search) {
        mav = new ModelAndView("bills");
        mav.addObject("billsList", billsService.search(search));
        return mav;
    }

    @RequestMapping(value = "/remove")
    public String trash(@RequestParam Integer id) {
        if (billsService.removeBill(id)) {
            return "redirect:bills";
        }
        return "bills";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute RecordForm form) {
        billsService.addBillFromForm(form);
        return "redirect:bills";
    }

}
