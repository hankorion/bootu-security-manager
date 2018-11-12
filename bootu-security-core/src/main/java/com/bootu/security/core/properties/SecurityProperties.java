package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bootu.security")
@Data
public class SecurityProperties {
    private WebProperties web = new WebProperties();
}
