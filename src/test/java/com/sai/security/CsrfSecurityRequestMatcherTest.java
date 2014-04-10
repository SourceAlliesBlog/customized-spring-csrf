package com.sai.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CsrfSecurityRequestMatcherTest {
    @InjectMocks
    private CsrfSecurityRequestMatcher matcher;
    private MockHttpServletRequest request;

    @Before
    public void setup() {
        request = new MockHttpServletRequest();
    }

    @Test
    public void methods() {
        request.setServletPath("/blah");
        request.setMethod("POST");
        assertTrue(matcher.matches(request));

        request.setMethod("GET");
        assertFalse(matcher.matches(request));

        request.setMethod("TRACE");
        assertFalse(matcher.matches(request));

        request.setMethod("HEAD");
        assertFalse(matcher.matches(request));

        request.setMethod("OPTIONS");
        assertFalse(matcher.matches(request));
    }

    @Test
    public void csrf_unprotected_urls() {
        request.setMethod("POST");

        request.setServletPath("/unprotected");
        assertFalse(matcher.matches(request));
    }

    @Test
    public void csrf_protected_urls() {
        request.setMethod("POST");

        request.setServletPath("/protected");
        assertTrue(matcher.matches(request));
    }

}
