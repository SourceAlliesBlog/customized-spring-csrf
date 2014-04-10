package com.sai.integration;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;

public class CsrfTokenBuilder {

    private String headerName;
    private String requestParameterName;
    private String tokenValue;

    public CsrfTokenBuilder() {

    }

    public CsrfTokenBuilder headerName(String headerName) {
        this.headerName = headerName;
        return this;
    }

    public CsrfTokenBuilder requestParameterName(String requestParameterName) {
        this.requestParameterName = requestParameterName;
        return this;
    }

    public CsrfTokenBuilder tokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
        return this;
    }

    public CsrfToken build() {
        return new DefaultCsrfToken(headerName, requestParameterName, tokenValue);
    }
}