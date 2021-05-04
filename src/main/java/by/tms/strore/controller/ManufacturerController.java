package by.tms.strore.controller;


import by.tms.strore.entity.Manufacturer;
import by.tms.strore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class ManufacturerController {
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/manufacturers")
    public ModelAndView manufacturers(ModelAndView modelAndView) {
        List<Manufacturer>  manufacturers =  manufacturerService.getAllManufacturers();
        modelAndView.addObject("manufacturer", new Manufacturer());
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("manufacturers");
        return modelAndView;
    }

    @PostMapping("/manufacturers")
    public ModelAndView manufacturers(@Valid @ModelAttribute("manufacturer") Manufacturer manufacturer, Errors errors, ModelAndView modelAndView) {
        manufacturerService.createManufacturer(manufacturer);
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        modelAndView.addObject("manufacturer", new Manufacturer());
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("manufacturers");
        return modelAndView;
    }
}
