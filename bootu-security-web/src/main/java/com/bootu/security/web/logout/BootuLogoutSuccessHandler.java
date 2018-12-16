package com.bootu.security.web.logout;

import com.bootu.security.core.dto.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class BootuLogoutSuccessHandler implements LogoutSuccessHandler {


    public BootuLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    private String signOutSuccessUrl;

    private ObjectMapper objectMapper = new ObjectMapper();

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.web.authentication.logout.
     * LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        log.info("Logout Success");

        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new BasicResponse("Success Logout")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }

    }
}
