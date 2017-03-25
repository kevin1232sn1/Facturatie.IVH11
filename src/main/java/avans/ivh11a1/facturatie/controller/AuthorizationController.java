package avans.ivh11a1.facturatie.controller;

import avans.ivh11a1.facturatie.domain.Exception.SecurityException;
import avans.ivh11a1.facturatie.domain.administration.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kevin on 21-3-2017.
 */
@ControllerAdvice
public class AuthorizationController {

    @ExceptionHandler(SecurityException.class)
    public ModelAndView handleCustomException(SecurityException ex) {
        ModelAndView model;
        if (ex.getType() == "NotLoggedIn" || ex.getType() == "LoginWrong") {
            model = new ModelAndView("auth/login");
            model.addObject("user", new User());
        }else{
            model = new ModelAndView("dashboard/index");
        }
        model.addObject("exception", ex.getMessage());
        return model;
    }
}
