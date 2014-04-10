package com.sai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "pages/login";
    }

    @RequestMapping("/login-fail")
    public ModelAndView loginFailure() {
        ModelAndView modelAndView = new ModelAndView("pages/login");
        modelAndView.addObject("errors", true);
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("pages/login");
        modelAndView.addObject("logout", true);
        return modelAndView;
    }
}
