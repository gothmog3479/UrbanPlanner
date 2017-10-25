package ru.gothmog.urbanplanner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @author d.grushetskiy
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user){
        ModelAndView modelAndView = new ModelAndView();

        if (user != null){
            modelAndView.addObject("errorMsg", "Привет " + user.getName() + ", у вас нет доступа к этой странице!");
        } else {
            modelAndView.addObject("errorMsg", "У вас нет доступа к этой странице!");
        }

        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }
//
//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public ModelAndView loginPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("forms/login");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
//    public String AccessDeniedPage() {
//        return "access_denied";
//    }
}
