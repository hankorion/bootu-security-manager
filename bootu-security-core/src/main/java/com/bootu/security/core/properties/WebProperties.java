package com.bootu.security.core.properties;

import com.bootu.security.core.enums.LoginTypeEnum;
import lombok.Data;

@Data
public class WebProperties {
    private String loginPage = "/bootu-signIn.html";
    private LoginTypeEnum loginType = LoginTypeEnum.JSON;
}
