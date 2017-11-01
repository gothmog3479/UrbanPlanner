package ru.gothmog.urbanplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.gothmog.urbanplanner.model.entities.registry.auth.EmployeeUser;
import ru.gothmog.urbanplanner.model.utility.MailConstructor;
import ru.gothmog.urbanplanner.model.utility.SecurityUtility;
import ru.gothmog.urbanplanner.service.EmployeeUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AccountController {


}
