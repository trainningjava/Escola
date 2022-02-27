package com.acc.escola.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ModelAndView viewDiscHome() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home/index");
        return model;
    }

}
