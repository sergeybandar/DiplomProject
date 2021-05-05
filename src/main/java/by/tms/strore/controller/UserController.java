package by.tms.strore.controller;


import by.tms.strore.entity.*;
import by.tms.strore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.addObject("user", new RegUser());
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @PostMapping("/registration")
    public ModelAndView registration(@Valid @ModelAttribute("user") RegUser regUser, Errors errors, ModelAndView modelAndView) {
        if (errors.hasErrors()) {
            modelAndView.setViewName("registration");
            return modelAndView;
        } else {
            User user = new User();
            user.setUserName(regUser.getUserName());
            user.setPassword(regUser.getPassword());
            user.setLogin(regUser.getLogin());
            userService.creatUser(user);
            modelAndView.setViewName("redirect:/authorization");
            return modelAndView;
        }
    }

    @GetMapping("/authorization")
    public ModelAndView authorization(ModelAndView modelAndView) {
        modelAndView.addObject("user", new AuthUser());
        modelAndView.setViewName("authorization");
        return modelAndView;
    }
    @PostMapping(path = "/authorization")
    public ModelAndView authorization(@Valid @ModelAttribute("user") AuthUser authUser, Errors errors, HttpSession session, ModelAndView modelAndView){
        if (errors.hasErrors()) {
            modelAndView.setViewName("authorization");
            return modelAndView;
        } else {
        String result = "";
        boolean isAuthorised = false;
        User byLogin = userService.getByLogin(authUser.getLogin());
        if (byLogin != null) {
            if (authUser.getPassword().equals(byLogin.getPassword())) {
                session.setAttribute("user", byLogin);
                session.setAttribute("isGuest", false);
                session.setAttribute("isUser", true);
                if (byLogin.getRole().equals(Role.MANAGER)){
                    session.setAttribute("isManager", true);
                }
                if (byLogin.getRole().equals(Role.ADMIN)){
                    session.setAttribute("isManager", true);
                    session.setAttribute("isAdmin", true);
                }
                System.out.println("Authorization complete");
                result = "Authorization complete";
                isAuthorised = true;
            } else {
                result = "incorrect password";
            }
        } else {
            result = "incorrect login";
        }
        if (isAuthorised) {
            modelAndView.setViewName("redirect:/");
        } else {
            modelAndView.addObject("result", result);
            modelAndView.setViewName("authorization");
        }}
        return modelAndView;
    }
    @GetMapping(path="/out")//GET localhost:8080/
    public ModelAndView logout(ModelAndView modelAndView, HttpSession session){
        session.invalidate();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    @GetMapping("/allInfo")
    public ModelAndView allInfo(ModelAndView modelAndView) {
        List<User> allUsers=userService.getAllUsers();
        modelAndView.addObject("users", allUsers);
        modelAndView.addObject("idModel", new IdModel());
        modelAndView.setViewName("allInfo");
        return modelAndView;
    }
    @PostMapping("/changeUserStatus")
    public ModelAndView changeUserStatus(@ModelAttribute("IdModel") IdModel idModel, ModelAndView modelAndView, HttpSession session) {
        User user=userService.getById(idModel.getIdModel());
        if (user.getRole().equals(Role.USER)) {
            user.setRole(Role.MANAGER);
        } else {
            user.setRole(Role.USER);
        }
        userService.updateUser(user, user.getUserName());
        List<User> allUsers=userService.getAllUsers();
        modelAndView.addObject("users", allUsers);
        modelAndView.addObject("idModel", new IdModel());
        modelAndView.setViewName("redirect:/allInfo");
        return modelAndView;
    }

    @Autowired
    DeviceNumberService deviceNumberService;
    @Autowired
    DeviceService deviceService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ManufacturerService manufacturerService;
    @GetMapping(path="/start")//GET localhost:8080/
    public ModelAndView start(ModelAndView modelAndView, HttpSession session){
        if(categoryService.getAllCategories().size()!=0){
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        Category Laptops= new Category("Laptops", "//content2.onliner.by/catalog/device/header/5d591589287bdfe724068e346033c6cc.jpeg");
        Category Tablets= new Category("Tablets", "//content2.onliner.by/catalog/device/header/8b4b1f659163bd944bd2e81f609102e6.jpeg");
        Category Monoblocks= new Category("Monoblocks", "//content2.onliner.by/catalog/device/header/951c22d1c0ae0012d7c2122ef53e67ea.jpeg");
        Category Computers= new Category("Computers", "//content2.onliner.by/catalog/device/header/64a3053d77e8091602fed57bb060985e.jpeg");
        Category Monitors= new Category("Monitors", "//content2.onliner.by/catalog/device/header/5b24886e24c1f02a5b744bb58c891b36.jpeg");
        Manufacturer ASUS=new Manufacturer("ASUS");
        Manufacturer Lenovo=new Manufacturer("Lenovo");
        Manufacturer HP=new Manufacturer("HP");
        Manufacturer Apple=new Manufacturer("Apple");
        Manufacturer Acer=new Manufacturer("Acer");
        Manufacturer Dell=new Manufacturer("Dell");
        Manufacturer MSI=new Manufacturer("MSI");
        Manufacturer Xiaomi=new Manufacturer("Xiaomi");
        Manufacturer Huawei=new Manufacturer("Huawei");
        Manufacturer Samsung=new Manufacturer("Samsung");
        Device device1=new Device(Laptops,
                Lenovo,
                "Lenovo IdeaPad S145-15API 81UT00MLRE",
                "15.6\" 1920 x 1080 TN+Film, 60 Гц, несенсорный, AMD 3020e 1200 МГц, 4 ГБ, SSD 256 ГБ, граф. адаптер: встроенный, без ОС, цвет крышки серый.",
                "960",
                "Китай",
                "https://content2.onliner.by/catalog/device/header/cd20709d431cc8390e19f59b4f4b1929.jpeg");
        Device device2=new Device(Laptops,
                Lenovo,
                "Lenovo IdeaPad Gaming 3 15ARH05 82EY00FGRE",
                "15.6\" 1920 x 1080 IPS, 120 Гц, несенсорный, AMD Ryzen 5 4600H 3000 МГц, 16 ГБ, SSD 512 ГБ, граф. адаптер: NVIDIA GeForce GTX 1650 Ti 4 ГБ, без ОС, цвет крышки черный",
                "2650",
                "Китай",
                "https://content2.onliner.by/catalog/device/header/4904e24d7e36e19818c954179881d5dc.jpeg");
        Device device3=new Device(Laptops,
                Lenovo,
                "Lenovo Legion Y540-15IRH 81SX008MPB",
                "15.6\" 1920 x 1080 IPS, 60 Гц, несенсорный, Intel Core i5 9300H 2400 МГц, 8 ГБ, SSD 256 ГБ, граф. адаптер: NVIDIA GeForce GTX 1660 Ti 6 ГБ, без ОС, цвет крышки черный",
                "2450",
                "Китай",
                "https://content2.onliner.by/catalog/device/header/f46b1b3576a46c92605dcd9b7c38d687.jpeg");
        Device device4=new Device(Laptops,
                Lenovo,
                "MSI Raider GE76 10UH-441RU",
                "17.3\" 1920 x 1080 IPS, 300 Гц, несенсорный, Intel Core i7 10870H 2200 МГц, 64 ГБ, SSD 2048 ГБ, граф. адаптер: NVIDIA GeForce RTX 3080 16 ГБ, Windows 10, цвет крышки темно-серый",
                "10400",
                "Китай",
                "https://content2.onliner.by/catalog/device/header/7b0069f31fe8d7be8ef53da8ff7c325d.jpeg");
        Device device5=new Device(Laptops,
                Lenovo,
                "MSI Stealth 15M A11SDK-032RU",
                "15.6\" 1920 x 1080 IPS, 144 Гц, несенсорный, Intel Core i7 1185G7 3000 МГц, 16 ГБ, SSD 512 ГБ, граф. адаптер: NVIDIA GeForce GTX 1660 Ti 6 ГБ, Windows 10, цвет крышки темно-серый",
                "4000",
                "Китай",
                "https://content2.onliner.by/catalog/device/header/c60999e7adb699bf6bd90d193027d04c.jpeg");

        categoryService.createCategory(Laptops);
        categoryService.createCategory(Tablets);
        categoryService.createCategory(Monoblocks);
        categoryService.createCategory(Computers);
        categoryService.createCategory(Monitors);

        manufacturerService.createManufacturer(ASUS);
        manufacturerService.createManufacturer(Lenovo);
        manufacturerService.createManufacturer(HP);
        manufacturerService.createManufacturer(Apple);
        manufacturerService.createManufacturer(Acer);
        manufacturerService.createManufacturer(Dell);
        manufacturerService.createManufacturer(MSI);
        manufacturerService.createManufacturer(Xiaomi);
        manufacturerService.createManufacturer(Huawei);
        manufacturerService.createManufacturer(Samsung);

        deviceService.createDevice(device1);
        deviceService.createDevice(device2);
        deviceService.createDevice(device3);
        deviceService.createDevice(device4);
        deviceService.createDevice(device5);
        User user=new User(1, "User", "User", "User");
        User Manager=new User(2, "Manager", "Manager", "Manager");
        Manager.setRole(Role.MANAGER);
        User Admin=new User(3, "Admin", "Admin", "Admin");
        Admin.setRole(Role.ADMIN);
        userService.creatUser(user);
        userService.creatUser(Manager);
        userService.creatUser(Admin);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
