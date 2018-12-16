package com.bootu.security.core.properties;

public interface SecurityConstants {

    /**
     * Default url for validate code
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";


    /**
     * Image validation, the parameter name in the request
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * SMS validation, the parameter name in the request
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * SMS validation, the parameter name in the request
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobileNo";


    String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";


    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**
     * default user name and password validation url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * default sms validation url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/sms";
    /**
     * default openid validation url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

    /**
     * Defaule login page
     *
     * @see SecurityController
     */
    String DEFAULT_SIGN_IN_PAGE_URL = "/bootu-signIn.html";

    String DEFAULT_SIGN_UP_PAGE_URL = "/bootu-signUp.html";

    String WEIXIN_URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    String WEIXIN_REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    /**
     * 微信获取授权码的url
     */
    String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 微信获取accessToken的url
     */
    String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 获取第三方用户信息的url
     */
    String DEFAULT_SOCIAL_USER_INFO_URL = "/social/user";

    /**
     * Session timeout/invalid url
     */
    String DEFAULT_SESSION_INVALID_URL = "/bootu-session-invalid.html";

    /**
     * Session timeout/invalid url
     */
    String DEFAULT_USER_REGISTER_URL = "/user/register";

    /**
     * Signout/Logout URL
     */
    String DEFAULT_SIGN_OUT_REQUEST_URL = "signOut";

}
