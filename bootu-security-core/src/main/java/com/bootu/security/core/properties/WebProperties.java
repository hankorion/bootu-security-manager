package com.bootu.security.core.properties;

import com.bootu.security.core.enums.LoginTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.web")
public class WebProperties {
    /**
     * Session management configuration
     */
    private SessionProperties session = new SessionProperties();
    private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    /**
     * The URL redirect to when sign out/logout, will return json data if not configure
     */
    private String signOutUrl;
    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
    private int rememberMeSeconds = 3600;
    private String singInSuccessUrl;
}
