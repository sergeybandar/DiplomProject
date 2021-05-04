package by.tms.strore.controller;

import by.tms.strore.entity.*;
import by.tms.strore.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    DeviceNumberService deviceNumberService;

    @GetMapping("/orders")
    public ModelAndView orders(ModelAndView modelAndView) {
//        List<Order> orders = orderService.getAllOrders();
//        System.out.println("/orders");
//        System.out.println(orders);
//        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders");
        return modelAndView;
    }
    @GetMapping("/order")
    public ModelAndView order(ModelAndView modelAndView) {
//        List<Order> orders = orderService.getAllOrders();
//        System.out.println("/orders");
//        System.out.println(orders);
//        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("order");
        return modelAndView;
    }
    @Autowired
    DeviceService deviceService;
    @PostMapping("/addOrder")
    public ModelAndView addOrder(ModelAndView modelAndView, HttpSession session) {
        Basket basket = (Basket) session.getAttribute("basket");
        User user = (User) session.getAttribute("user");
        List<DeviceNumber> deviceNumberList = basket.getDeviceNumbers();
        List<DeviceNumber> deviceNumberListTrue = new ArrayList<>();
        for (int i = 0; i < deviceNumberList.size(); i++) {
            deviceNumberListTrue.add(deviceNumberService.createDeviceNumber(new DeviceNumber(deviceService.findDeviceByName(deviceNumberList.get(i).getDevice().getName()),deviceNumberList.get(i).getNumber())));
        }
        User trueUser = userService.getByName(user.getUserName());
        Order order = new Order(trueUser, deviceNumberListTrue);
        orderService.createOrder(order);
        List<Order> orders = orderService.getAllOrdersByUser(user);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("redirect:/");
        return modelAndView;

    }

}
