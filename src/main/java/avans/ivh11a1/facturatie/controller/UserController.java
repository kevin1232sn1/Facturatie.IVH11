package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.administration.User;
import avans.ivh11a1.facturatie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Robin on 15-10-16.
 * Controller for the User domain.
 * This controller regulates the mapping of the user's CRUD pages.
 *
 * @author Robin Valk
 * @version 1.0
 * @see User
 * @see UserRepository
 * @see HttpSession
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    /**
     * Defines `module` variable for usage in the sidebar
     *
     * @return String
     */
    @ModelAttribute("page")
    public String module() {
        return "users";
    }

    /**
     * Mapping of the overview page.
     *
     * @param model
     * @return user/index
     */
    @GetMapping(value = "")
    String index(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "user/index";
    }

    /**
     * Mapping of the create page.
     *
     * @param model
     * @return user/edit
     */
    @GetMapping(value = "/create")
    String create(Model model) {
        model.addAttribute("user", new User());

        return "user/edit";
    }

    /**
     * Mapping of the store request.
     * Restore old password when no new password is given.
     *
     * @param model
     * @param session
     * @param user
     * @param password
     * @return user/edit
     */

    //String store(Model model, HttpSession session, @ModelAttribute User user, @RequestParam("password") String password) {
    @PostMapping(value = "/create")
    String store(@Valid User user, final BindingResult bindingResult, Model model, HttpSession session, @RequestParam("password") String password) {
        // reset old password when password field was empty and request came from edit page
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        if (password.equals("") && session.getAttribute("oldPassword") != null) {
            user.setPasswordWithoutHash(session.getAttribute("oldPassword").toString());
        }

        userRepository.save(user);

        model.addAttribute("success", "User successfully saved");

        return this.index(model);
    }

    /**
     * Mapping of the edit page.
     * Store current password for recovery later.
     *
     * @param model
     * @param session
     * @param id
     * @return user/edit
     */
    @GetMapping(value = "/edit/{id}")
    String edit(Model model, HttpSession session, @PathVariable int id) {
        User user = userRepository.findOne(id);
        model.addAttribute("user", user);
        session.setAttribute("oldPassword", user.getPassword());

        return "user/edit";
    }

    /**
     * Mapping of the delete request.
     *
     * @param model
     * @param session
     * @param id
     * @return user/edit
     */
    @GetMapping(value = "/delete/{id}")
    String delete(Model model, HttpSession session, @PathVariable int id) {
        userRepository.delete(id);

        model.addAttribute("success", "User successfully removed");

        return this.index(model);
    }
}
