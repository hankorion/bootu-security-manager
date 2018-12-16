package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.social")
public class SocialProperties {

    private String filterProcessesUrl = "/auth";
    private QQProperties qq = new QQProperties();
    private WeixinProperties weixin = new WeixinProperties();

}
