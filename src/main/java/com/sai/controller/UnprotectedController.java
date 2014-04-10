package com.sai.controller;

import com.sai.forms.NameRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UnprotectedController {
    @RequestMapping(value = "/unprotected", method = RequestMethod.GET)
    public ModelAndView unprotectedGetUrl() {
        ModelAndView modelAndView = new ModelAndView("unprotected");
        modelAndView.addObject("nameRequest", new NameRequest());
        return modelAndView;
    }

    @RequestMapping(value = "/unprotected", method = RequestMethod.POST)
    public ModelAndView unprotectedPostUrl(NameRequest nameRequest, RedirectAttributes redirectAttributes) {
        String message = String.format("Your name is: %s %s", nameRequest.getFirst(), nameRequest.getLast());
        redirectAttributes.addFlashAttribute("message", message);
        return new ModelAndView(new RedirectView("/unprotected", true, true, false));
    }
}
