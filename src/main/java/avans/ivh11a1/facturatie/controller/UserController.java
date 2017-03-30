package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.aop.SecurityAnnotation;
import avans.ivh11a1.facturatie.domain.administration.Role;
import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@SecurityAnnotation(allowedRole = { Role.ADMIN, Role.ADMINISTRATION })
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("page")
    public String module() {
        return "users";
    }

    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("users", userService.findAll());

        return "user/index";
    }

    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("user", new User());

        return "user/edit";
    }

    @PostMapping(value = "/create")
    String store(@Valid User user,  final BindingResult bindingResult, Model model, @RequestParam("role") String role) {
        user.setRole(Role.getRoleByName(role));

        userService.save(user);

        model.addAttribute("success", "User successfully saved");

        return this.index(model);
    }

    @GetMapping(value = "/edit/{id}")
    String edit(Model model, HttpSession session, @PathVariable int id) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @GetMapping(value = "/delete/{id}")
    String delete(Model model, HttpSession session, @PathVariable int id) {
        userService.deleteById(id);

        model.addAttribute("success", "User successfully removed");

        return this.index(model);
    }
}
