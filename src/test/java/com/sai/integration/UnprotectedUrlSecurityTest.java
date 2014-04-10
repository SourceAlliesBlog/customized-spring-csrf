package com.sai.integration;

import com.sai.controller.UnprotectedController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:contexts/ApplicationContext.xml")
public class UnprotectedUrlSecurityTest extends BaseSecuritySupport {
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @Mock
    private Principal principal;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new UnprotectedController())
                .addFilter(this.springSecurityFilterChain).build();
        when(principal.getName()).thenReturn(USERNAME);
    }

    @Test
    public void get_unprotected() throws Exception {
        HttpSession session = login();

        mockMvc.perform(get("/unprotected")
                .principal(principal)
                .session((MockHttpSession) session))
                .andExpect(status().isOk());
    }

    @Test
    public void post_unprotected() throws Exception {
        HttpSession session = login();

        mockMvc.perform(post("/unprotected")
                .principal(principal)
                .session((MockHttpSession) session))
                .andExpect(redirectedUrl("/unprotected"));
    }

}
