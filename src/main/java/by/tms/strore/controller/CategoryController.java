package by.tms.strore.controller;

import by.tms.strore.entity.*;
import by.tms.strore.service.CategoryService;
import by.tms.strore.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    DeviceService deviceService;

    @GetMapping("/categories")
    public ModelAndView categories(ModelAndView modelAndView) {
        List<Category> categories = categoryService.getAllCategories();
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("categories");
        return modelAndView;
    }

    @PostMapping("/categories")
    public ModelAndView categories(@Valid @ModelAttribute("category") Category category, Errors errors, ModelAndView modelAndView) {
        if (errors.hasErrors()) {
            List<Category> categories = categoryService.getAllCategories();
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("categories", categories);
            modelAndView.setViewName("categories");
            return modelAndView;
        } else {
            categoryService.createCategory(category);
            List<Category> categories = categoryService.getAllCategories();
            modelAndView.addObject("category", new Category());
            modelAndView.addObject("categories", categories);
            modelAndView.setViewName("categories");
            return modelAndView;
        }
    }

    @GetMapping("/categories/{name}")
    public ModelAndView category(@PathVariable("name") String name, ModelAndView modelAndView) {
        Category category = categoryService.findCategoryByName(name);
        List<Device> devices = deviceService.getAllDevicesByCategory(category);
        modelAndView.addObject("devices", devices);
        modelAndView.addObject("basketModel", new BasketModel());
        modelAndView.setViewName("category");
        return modelAndView;
    }
}
