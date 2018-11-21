package com.bootu.security.web;

import com.bootu.security.core.properties.SecurityProperties;
import com.bootu.security.web.logout.BootuLogoutSuccessHandler;
import com.bootu.security.web.session.BootuInvalidSessionStrategy;
import com.bootu.security.web.session.BootuSessionExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class BootuSecurityBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * session失效时的处理策略配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(){
        return new BootuInvalidSessionStrategy(securityProperties);
    }

    /**
     * 并发登录导致前一个session失效时的处理策略配置
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
        return new BootuSessionExpiredSessionStrategy(securityProperties);
    }
    /**
     * 退出时的处理策略配置
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new BootuLogoutSuccessHandler(securityProperties.getWeb().getSignOutUrl());
    }

}
