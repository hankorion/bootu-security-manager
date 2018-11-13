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
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认的OPENID登录请求处理url
     */
    String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

}
