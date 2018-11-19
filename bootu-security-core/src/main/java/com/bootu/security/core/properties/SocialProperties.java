package com.bootu.security.core.properties;

import lombok.Data;

@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";
    private QQProperties qq = new QQProperties();

}
