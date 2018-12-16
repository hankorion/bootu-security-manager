package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bootu.security.code.image")
public class ImageCodeProperties extends BasicCodeProperties {

    // Image width
    private int width = 160;
    // image height
    private int height = 40;
    // mesh line numbers
    private int lineCount = 150;

}
