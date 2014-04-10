package com.sai.controller;

import com.sai.forms.NameRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProtectedControllerTest {
    @InjectMocks
    private ProtectedController controller;
    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    public void protectedGet(){
        ModelAndView modelAndView = controller.protectedGetUrl();
        assertEquals("protected", modelAndView.getViewName());
        assertTrue(modelAndView.getModel().get("nameRequest") instanceof NameRequest);
    }

    @Test
    public void protectedPost(){
        NameRequest nameRequest = new NameRequest();
        nameRequest.setFirst("First");
        nameRequest.setLast("Last");
        ModelAndView modelAndView = controller.protectedPostUrl(nameRequest, redirectAttributes);

        RedirectView rv = (RedirectView) modelAndView.getView();
        assertEquals("/protected", rv.getUrl());
        assertTrue(rv.isRedirectView());
        assertFalse(rv.isExposePathVariables());

        verify(redirectAttributes).addFlashAttribute("message", "Your name is: First Last");
    }

}
