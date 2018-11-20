package com.bootu.security.core.social.weixin.connect;

import com.bootu.security.core.properties.SecurityConstants;
import com.bootu.security.core.social.weixin.api.Weixin;
import com.bootu.security.core.social.weixin.api.impl.WeixinImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {

    /**
     * @param appId
     * @param appSecret
     */
    public WeixinServiceProvider(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret, SecurityConstants.WEIXIN_URL_AUTHORIZE, SecurityConstants.WEIXIN_URL_ACCESS_TOKEN));
    }


    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
     */
    @Override
    public Weixin getApi(String accessToken) {
        return new WeixinImpl(accessToken);
    }
}
