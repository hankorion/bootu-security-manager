package com.bootu.security.core.social.qq.api.impl;

import com.bootu.security.core.properties.QQOAuth2Constants;
import com.bootu.security.core.social.qq.api.QQ;
import com.bootu.security.core.social.qq.api.QQUserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        this.appId = appId;
        String url = String.format(QQOAuth2Constants.URL_GET_OPENID, accessToken);
        log.info("Connect to QQ, get openid url [{}]",url);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("Connect to QQ, get openid result [{}]",result);
        try {
            JSONObject qqResultObj = new JSONObject(result);
            //this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
            this.openId = qqResultObj.getString("openid");
        }catch (Exception e){
            log.error("qqResultObj [{}]",e);
            throw new RuntimeException("Failed to get user openid: ", e);
        }

    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(QQOAuth2Constants.URL_GET_USERINFO, appId, openId);
        log.info("Connect to QQ, get user info url [{}]",url);
        String result = getRestTemplate().getForObject(url, String.class);
        log.info("Connect to QQ, get user info result [{}]",result);

        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result, QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get user info: ", e);
        }
    }
}
