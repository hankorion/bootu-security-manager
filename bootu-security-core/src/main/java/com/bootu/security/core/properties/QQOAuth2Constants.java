package com.bootu.security.core.properties;

public interface QQOAuth2Constants {

    String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
}
