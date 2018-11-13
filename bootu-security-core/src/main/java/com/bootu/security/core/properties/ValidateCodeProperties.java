package com.bootu.security.core.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private BasicCodeProperties sms = new BasicCodeProperties();
}
