package com.sai.integration;

import com.sai.controller.ProtectedController;
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
public class ProtectedUrlSecurityTest extends BaseSecuritySupport {
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
    @Mock
    private Principal principal;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProtectedController())
                .addFilter(this.springSecurityFilterChain).build();
        when(principal.getName()).thenReturn(USERNAME);
    }

    @Test
    public void get_protected() throws Exception {
        HttpSession session = login();

        mockMvc.perform(get("/protected")
                .principal(principal)
                .session((MockHttpSession) session))
                .andExpect(status().isOk());
    }

    @Test
    public void post_protected() throws Exception {
        HttpSession session = login();

        mockMvc.perform(post("/protected")
                .principal(principal)
                .sessionAttr(CSRF_TOKEN_SESSION_ATTRIBUTE_NAME, csrf())
                .param(CSRF_TOKEN_REQUEST_PARAM_NAME, CSRF_TOKEN_VALUE)
                .session((MockHttpSession) session))
                .andExpect(redirectedUrl("/protected"));
    }

}
