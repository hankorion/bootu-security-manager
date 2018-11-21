package com.bootu.security.web.session;

import com.bootu.security.core.properties.SecurityProperties;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BootuInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public BootuInvalidSessionStrategy(SecurityProperties securityProperties) {
        super(securityProperties);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        onSessionInvalid(request, response);
    }

}