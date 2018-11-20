package com.bootu.security.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

@Data
public class WeixinProperties extends SocialProperties{
    private String providerId = "weixin";
}
