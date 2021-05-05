package by.tms.strore.controller;

import by.tms.strore.entity.AuthUser;
import by.tms.strore.entity.RegUser;
import by.tms.strore.exception.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userIsExistException(UserAlreadyExistsException userAlreadyExistsException, Model model) {
        model.addAttribute("message", "User already exists");
        model.addAttribute("user", new RegUser());
        return "registration";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String userIsNotFoundException(UserNotFoundException userNotFoundException, Model model) {
        model.addAttribute("message", "User is not found");
        model.addAttribute("user", new AuthUser());
        return "authorization";
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public String categoryIsExistException(CategoryAlreadyExistsException categoryAlreadyExistsException, Model model) {
        model.addAttribute("message", categoryAlreadyExistsException.getMessage());
        return "categoryExistException";
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public String categoryIsNotFoundException(CategoryNotFoundException categoryNotFoundException, Model model) {
        model.addAttribute("message", categoryNotFoundException.getMessage());
        return "categoryIsNotFoundException";
    }


    @ExceptionHandler(DeviceNotFoundException.class)
    public String technicIsNotFoundException(DeviceNotFoundException technicNotFoundException, Model model) {
        model.addAttribute("message", technicNotFoundException.getMessage());
        return "DeviceIsNotFoundException";
    }

    @ExceptionHandler(DeviceAlreadyExistsException.class)
    public String technicIsExistException(DeviceAlreadyExistsException technicAlreadyExistsException, Model model) {
        model.addAttribute("message", technicAlreadyExistsException.getMessage());
        return "DeviceIsExistException";
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public String orderIsNotFoundException(OrderNotFoundException orderNotFoundException, Model model) {
        model.addAttribute("message", orderNotFoundException.getMessage());
        return "orderIsNotFoundException";
    }

}
