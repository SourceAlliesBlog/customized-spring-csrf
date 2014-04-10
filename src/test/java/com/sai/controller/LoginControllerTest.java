package com.sai.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {
    @InjectMocks
    private LoginController loginController;

    @Test
    public void login(){
        assertEquals("pages/login", loginController.login());
    }

    @Test
    public void loginFailure(){
        ModelAndView modelAndView = loginController.loginFailure();
        assertEquals(true, modelAndView.getModelMap().get("errors"));
        assertEquals("pages/login", modelAndView.getViewName());
    }

    @Test
    public void logout(){
        ModelAndView modelAndView = loginController.logout();
        assertEquals(true, modelAndView.getModelMap().get("logout"));
        assertEquals("pages/login", modelAndView.getViewName());
    }

}
