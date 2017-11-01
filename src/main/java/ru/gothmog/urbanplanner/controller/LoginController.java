package ru.gothmog.urbanplanner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.utility.MailConstructor;
import ru.gothmog.urbanplanner.model.utility.SecurityUtility;
import ru.gothmog.urbanplanner.service.EmployeeUserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.UUID;

/**
 * @author d.grushetskiy
 */
@Controller
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MailConstructor mailConstructor;

    @Autowired
    private EmployeeUserService userService;

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public String forgetPassword(HttpServletRequest request, @ModelAttribute("email") String email, Model model){

        model.addAttribute("classActiveForgetPassword", true);

        EmployeeUser user = userService.findByEmail(email);

        if (user == null){
            model.addAttribute("emailNotExist", true);
            return "myAccount";
        }

        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        //user.setPrePassword(encryptedPassword);
        userService.save(user);

        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        String appUrl = "http://" + request.getServerName() + ":"+request.getServerPort()+request.getContextPath();

        SimpleMailMessage newEmail = mailConstructor.constructResetTokenEmail(appUrl, request.getLocale(), token, user, password);

        mailSender.send(newEmail);

        model.addAttribute("forgetPasswordEmailSent", "true");

        return "myAccount";
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String newUserPost(HttpServletRequest request, @ModelAttribute("email") String userEmail, @ModelAttribute("username") String username){

        return "myAccount";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {
        ModelAndView modelAndView = new ModelAndView();

        if (user != null) {
            modelAndView.addObject("errorMsg", "Привет " + user.getName() + ", у вас нет доступа к этой странице!");
        } else {
            modelAndView.addObject("errorMsg", "У вас нет доступа к этой странице!");
        }

        modelAndView.setViewName("accessDenied");
        return modelAndView;
    }
}
