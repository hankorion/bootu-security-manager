package com.bootu.security.core.properties;

import lombok.Data;

@Data
public class BasicCodeProperties {

    // count of code
    private int codeCount = 4;
    // code expire after x seconds
    private int expireAfter = 60;

    private String url;
}
