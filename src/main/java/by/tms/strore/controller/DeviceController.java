package by.tms.strore.controller;


import by.tms.strore.entity.*;
import by.tms.strore.service.CategoryService;
import by.tms.strore.service.DeviceService;
import by.tms.strore.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class DeviceController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ManufacturerService manufacturerService;

    @GetMapping("/devices")
    public ModelAndView devices(ModelAndView modelAndView) {
        List<Device> devices = deviceService.getAllDevices();
        modelAndView.addObject("device", new Device());
        modelAndView.addObject("devices", devices);
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("devices");
        return modelAndView;
    }

    @PostMapping("/devices")
    public ModelAndView devices(@Valid @ModelAttribute("device") DeviceModel deviceModel, Errors errors, ModelAndView modelAndView) {
        Category category = categoryService.findCategoryByName(deviceModel.getCategory());
        Manufacturer manufacturer = manufacturerService.findManufacturerByName(deviceModel.getManufacturer());
        Device device = new Device(category, manufacturer, deviceModel.getName(), deviceModel.getDescription(), deviceModel.getPrice(), deviceModel.getCountry(), deviceModel.getUrl());
        deviceService.createDevice(device);
        List<Device> devices = deviceService.getAllDevices();
        modelAndView.addObject("device", new Device());
        modelAndView.addObject("devices", devices);
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("categories", categories);
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        modelAndView.addObject("manufacturers", manufacturers);
        modelAndView.setViewName("devices");
        return modelAndView;
    }

    @GetMapping("/categories/{name}/{id}")
    public ModelAndView device(@PathVariable("id") long id, ModelAndView modelAndView) {
        Device device = deviceService.findDeviceById(id);
        modelAndView.addObject("device", device);
        modelAndView.setViewName("device");
        return modelAndView;
    }

    @PostMapping("/addDevice")
    public ModelAndView addDevice(@ModelAttribute("basketModel") BasketModel basketModel, Errors errors, HttpSession session, ModelAndView modelAndView) {
        Device device = deviceService.findDeviceById(basketModel.getId());
        if (session.getAttribute("basket") == null) {
            Basket basket = new Basket();
            basket.addDevice(device);
            session.setAttribute("basket", basket);
        } else {
            Basket basket = (Basket) session.getAttribute("basket");
            basket.addDevice(device);
        }
        modelAndView.setViewName("redirect:/"+basketModel.getRedirect());
        return modelAndView;
    }

    @GetMapping("/basket")
    public ModelAndView basket(ModelAndView modelAndView) {
        modelAndView.setViewName("basket");
        return modelAndView;
    }
}
