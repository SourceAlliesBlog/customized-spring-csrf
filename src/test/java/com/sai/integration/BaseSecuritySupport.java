package com.sai.integration;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public abstract class BaseSecuritySupport {
    public static final String CSRF_TOKEN_HEADER_NAME = "X-CSRF-TOKEN";
    public static final String CSRF_TOKEN_REQUEST_PARAM_NAME = "_csrf";
    public static final String CSRF_TOKEN_SESSION_ATTRIBUTE_NAME =
            HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN");
    public static final String CSRF_TOKEN_VALUE = "f416e226-bebc-401d-a1ed-f10f47aa9c56";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    protected MockMvc mockMvc;

    public abstract void setup() throws Exception;

    protected HttpSession login() throws Exception {
        return mockMvc.perform(post("/j_spring_security_check")
                .param("j_username", USERNAME)
                .param("j_password", PASSWORD)
                .param(CSRF_TOKEN_REQUEST_PARAM_NAME, CSRF_TOKEN_VALUE)
                .sessionAttr(CSRF_TOKEN_SESSION_ATTRIBUTE_NAME, csrf()))
                .andExpect(redirectedUrl("/protected"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    protected CsrfToken csrf() {
        return new CsrfTokenBuilder()
                .headerName(CSRF_TOKEN_HEADER_NAME)
                .requestParameterName(CSRF_TOKEN_REQUEST_PARAM_NAME)
                .tokenValue(CSRF_TOKEN_VALUE)
                .build();
    }
}
