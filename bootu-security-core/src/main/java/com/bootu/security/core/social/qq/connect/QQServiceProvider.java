package com.bootu.security.core.social.qq.connect;

import com.bootu.security.core.properties.QQOAuth2Constants;
import com.bootu.security.core.social.qq.api.QQ;
import com.bootu.security.core.social.qq.api.impl.QQImpl;
import org.json.JSONException;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    /**
     * Create a new {@link OAuth2ServiceProvider}.
     *
     * @param oauth2Operations the OAuth2Operations template for conducting the OAuth 2 flow with the provider.
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQOAuth2Constants.URL_AUTHORIZE, QQOAuth2Constants.URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
