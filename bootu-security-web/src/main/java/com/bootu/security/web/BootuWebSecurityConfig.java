package com.bootu.security.web;

import com.bootu.security.core.authentication.FormAuthenticationConfig;
import com.bootu.security.core.authentication.sms.SmsCodeAuthenticationSecurityConfig;
import com.bootu.security.core.authorize.AuthorizeConfigManager;
import com.bootu.security.core.properties.SecurityProperties;
import com.bootu.security.core.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class BootuWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private SpringSocialConfigurer bootuSocialSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formAuthenticationConfig.configure(http);

        http
                .apply(validateCodeSecurityConfig)
                    .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .apply(bootuSocialSecurityConfig)
                    .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getWeb().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                    .and()
                .sessionManagement()
                    .invalidSessionStrategy(invalidSessionStrategy)
                    .maximumSessions(securityProperties.getWeb().getSession().getMaximumSessions())
                    .maxSessionsPreventsLogin(securityProperties.getWeb().getSession().isMaxSessionsPreventsLogin())
                    .expiredSessionStrategy(sessionInformationExpiredStrategy)
                    .and()
                    .and()
                .csrf().disable();

        authorizeConfigManager.config(http.authorizeRequests());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }
}
