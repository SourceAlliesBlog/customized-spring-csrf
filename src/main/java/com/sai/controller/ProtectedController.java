package com.sai.controller;

import com.sai.forms.NameRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProtectedController {
    @RequestMapping(value = "/protected", method = RequestMethod.GET)
    public ModelAndView protectedGetUrl() {
        ModelAndView modelAndView = new ModelAndView("pages/protected");
        modelAndView.addObject("nameRequest", new NameRequest());
        return modelAndView;
    }

    @RequestMapping(value = "/protected", method = RequestMethod.POST)
    public ModelAndView protectedPostUrl(NameRequest nameRequest, RedirectAttributes redirectAttributes) {
        String message = String.format("Your name is: %s %s", nameRequest.getFirst(), nameRequest.getLast());
        redirectAttributes.addFlashAttribute("message", message);
        return new ModelAndView(new RedirectView("/protected", true, true, false));
    }
}
