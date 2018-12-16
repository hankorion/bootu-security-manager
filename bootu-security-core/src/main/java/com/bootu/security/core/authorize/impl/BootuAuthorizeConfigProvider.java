package com.bootu.security.core.authorize.impl;

import com.bootu.security.core.authorize.AuthorizeConfigProvider;
import com.bootu.security.core.properties.SecurityConstants;
import com.bootu.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;


@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class BootuAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {

        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_FORM,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE,
                SecurityConstants.DEFAULT_SIGN_IN_PROCESSING_URL_OPENID,
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                securityProperties.getWeb().getLoginPage(),
                securityProperties.getWeb().getSignUpUrl(),
                securityProperties.getWeb().getSession().getSessionInvalidUrl()
        ).permitAll();
        if (StringUtils.isNotBlank(securityProperties.getWeb().getSignOutUrl())) {
            config.antMatchers(securityProperties.getWeb().getSignOutUrl()).permitAll();
        }
        return false;
    }
}