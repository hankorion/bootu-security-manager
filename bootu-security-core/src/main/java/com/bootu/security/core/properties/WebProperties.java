package com.bootu.security.core.properties;

import com.bootu.security.core.enums.LoginTypeEnum;
import lombok.Data;

@Data
public class WebProperties {
    //private String loginPage = "/bootu-signIn.html";
    private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
    private int rememberMeSeconds = 3600;
    private String singInSuccessUrl;
}
