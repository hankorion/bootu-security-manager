package com.bootu.security.service.authentication;

import com.bootu.security.core.dto.BasicResponse;
import com.bootu.security.core.enums.LoginTypeEnum;
import com.bootu.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("bootuAuthenticationFailureHandler")
@Slf4j
public class BootuAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("Login Failed [{}]", e);
        if (LoginTypeEnum.JSON.equals(securityProperties.getWeb().getLoginType())) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new BasicResponse(e.getMessage())));
        } else {
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
        }

    }
}
