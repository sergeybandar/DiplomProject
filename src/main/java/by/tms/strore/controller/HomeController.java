package by.tms.strore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    @GetMapping(path="/")//GET localhost:8080/
    public ModelAndView index(ModelAndView modelAndView, HttpSession session){
        modelAndView.addObject("name", "test");
        modelAndView.setViewName("home");
        if(session.getAttribute("startData")==null) {
            modelAndView.setViewName("redirect:/start");
            session.setAttribute("startData", true);
        }
        return modelAndView;
    }
}
