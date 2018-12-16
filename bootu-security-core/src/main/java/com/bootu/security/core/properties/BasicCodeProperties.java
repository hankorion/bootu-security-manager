package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.code.sms")
public class BasicCodeProperties {

    // count of code
    private int codeCount = 4;
    // code expire after x seconds
    private int expireAfter = 60;

    private String url;
}
