package ru.gothmog.urbanplanner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    private static final Logger LOG = LoggerFactory.getLogger(AppController.class);

    @RequestMapping("/")
    public String hello (Model model){
        model.addAttribute("hello", "Hello HH!");
        return "index";
    }
}
