package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.code")
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private BasicCodeProperties sms = new BasicCodeProperties();
}
