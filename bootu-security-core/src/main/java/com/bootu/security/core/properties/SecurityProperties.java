package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "bootu.security")
public class SecurityProperties {
    private WebProperties web = new WebProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    private SocialProperties social = new SocialProperties();
}
