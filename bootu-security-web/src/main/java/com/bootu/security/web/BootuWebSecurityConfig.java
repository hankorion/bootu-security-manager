package com.bootu.security.web;

import com.bootu.security.core.authentication.FormAuthenticationConfig;
import com.bootu.security.core.authentication.sms.SmsCodeAuthenticationSecurityConfig;
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

import javax.sql.DataSource;

@Configuration
public class BootuWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private AuthenticationSuccessHandler bootuAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler bootuAuthenticationFailureHandler;


    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationFailureHandler(bootuAuthenticationFailureHandler);
//        validateCodeFilter.setSecurityProperties(securityProperties);
//        validateCodeFilter.afterPropertiesSet();

        formAuthenticationConfig.configure(http);

        http
                .apply(validateCodeSecurityConfig)
                    .and()
//                .successHandler(bootuAuthenticationSuccessHandler)
//                .failureHandler(bootuAuthenticationFailureHandler)
//                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getWeb().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                    .and()
                .authorizeRequests()
                .antMatchers("/authentication/require"
                        , securityProperties.getWeb().getLoginPage()
                        , "/code/*").permitAll()
                .anyRequest()
                .authenticated()
                    .and()
                .csrf().disable();
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
