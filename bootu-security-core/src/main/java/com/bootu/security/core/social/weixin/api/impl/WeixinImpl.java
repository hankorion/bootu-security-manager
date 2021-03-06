package com.bootu.security.core.social.weixin.api.impl;

import com.bootu.security.core.properties.SecurityConstants;
import com.bootu.security.core.social.weixin.api.Weixin;
import com.bootu.security.core.social.weixin.api.WeixinUserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

    private ObjectMapper objectMapper = new ObjectMapper();

    public WeixinImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }

    @Override
    public WeixinUserInfo getUserInfo(String openId) {
        String url = SecurityConstants.WEIXIN_URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if (StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeixinUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeixinUserInfo.class);
        } catch (Exception e) {
            log.error("oojectmapper error [{}]", e);
        }
        return profile;
    }


}
