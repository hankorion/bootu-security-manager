package com.bootu.security.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties {

    // Image width
    private int width = 160;
    // image height
    private int height = 40;
    // count of code
    private int codeCount = 5;
    // mesh line numbers
    private int lineCount = 150;
    // code expire after x seconds
    private int expireAfter = 60;

    private String url;
}
