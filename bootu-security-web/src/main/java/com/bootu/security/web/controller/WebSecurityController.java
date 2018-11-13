package com.bootu.security.web.controller;

import com.bootu.security.core.properties.SecurityConstants;
import com.bootu.security.core.properties.SecurityProperties;
import com.bootu.security.web.dto.BasicResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class WebSecurityController {

    private RequestCache reqCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public BasicResponse requiredAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = reqCache.getRequest(request,response);
        if(savedRequest != null){
            String targetURl = savedRequest.getRedirectUrl();
            log.info("Target URL [{}]",targetURl);
            if(StringUtils.endsWithIgnoreCase(targetURl, ".html")){
                log.info("Config Login [{}]",securityProperties.getWeb().getLoginPage());
                redirectStrategy.sendRedirect(request,response,securityProperties.getWeb().getLoginPage());
            }
        }

        return new BasicResponse("Requied Sign In");
    }
}
