package by.tms.strore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path="/")//GET localhost:8080/
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.addObject("name", "test");
        modelAndView.setViewName("home");
//        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
