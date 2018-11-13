package com.bootu.security.core.properties;

import lombok.Data;

@Data
public class ImageCodeProperties extends BasicCodeProperties {

    // Image width
    private int width = 160;
    // image height
    private int height = 40;
    // mesh line numbers
    private int lineCount = 150;

}
