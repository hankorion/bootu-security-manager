package com.bootu.security.core.validate.code;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            if(StringUtils.equals("/authentication/form", httpServletRequest.getRequestURI())
        && StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException {
        ImageCode codeFromSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
        String codeFromRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(),"imageCode");

        if(StringUtils.isBlank(codeFromRequest)){
            throw new ValidateCodeException("Code cannot be empty");
        }

        if(codeFromSession == null){
            throw  new ValidateCodeException("Code not found");
        }

        if(codeFromSession.isExpired()){
            sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("code expired");
        }

        if(!StringUtils.equals(codeFromSession.getCode(), codeFromRequest)){
            throw new ValidateCodeException("code not match");
        }
        log.info("codeFromSession = [{}] , codeFromRequest = [{}]",codeFromSession.getCode(), codeFromRequest);
        sessionStrategy.removeAttribute(servletWebRequest,ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
