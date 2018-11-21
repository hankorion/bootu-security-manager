package com.bootu.security.core.properties;

import com.bootu.security.core.enums.LoginTypeEnum;
import lombok.Data;

@Data
public class WebProperties {
    /**
     * session管理配置项
     */
    private SessionProperties session = new SessionProperties();
    private String loginPage = SecurityConstants.DEFAULT_SIGN_IN_PAGE_URL;
    /**
     * 退出成功时跳转的url，如果配置了，则跳到指定的url，如果没配置，则返回json数据。
     */
    private String signOutUrl;
    private String signUpUrl = SecurityConstants.DEFAULT_SIGN_UP_PAGE_URL;
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
    private int rememberMeSeconds = 3600;
    private String singInSuccessUrl;
}
