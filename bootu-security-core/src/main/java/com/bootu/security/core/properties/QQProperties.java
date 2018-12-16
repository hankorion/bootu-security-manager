package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.social.qq")
public class QQProperties extends SocialProperties {
    private String providerId = "qq";
}
