package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.Exception.SecurityException;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.service.UserAdministrationService;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
class DashboardController {

    final
    UserService userService;

    final
    UserAdministrationService userAdministrationService;

    @Autowired
    public DashboardController(UserService userService, UserAdministrationService userAdministrationService) {
        this.userService = userService;
        this.userAdministrationService = userAdministrationService;
    }

    @ModelAttribute("page")
    public String module() {
        return "forward:/";
    }

    @RequestMapping(value = "/")
    String index(Model model) {
        model.addAttribute("dashboardData", userService.getDashboardData());
        return "dashboard/index";
    }

    @PostMapping(value = "/login")
    String Login(Model model, @ModelAttribute User user, HttpSession session) throws SecurityException {
        boolean succes = userService.loginUser(user);
        if (succes){
            return "forward:/";
        }else {
            throw new SecurityException("Sorry, that login was invalid. Please try again.", "LoginWrong");
        }
    }

    @PostMapping(value = "/logout")
    String Logout(Model model, HttpSession session) {;
        userService.logoutUser();
        return "forward:/";
    }
}
