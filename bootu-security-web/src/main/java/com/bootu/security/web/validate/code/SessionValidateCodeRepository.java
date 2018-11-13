package com.bootu.security.web.validate.code;

import com.bootu.security.core.validate.code.ValidateCode;
import com.bootu.security.core.validate.code.ValidateCodeRepository;
import com.bootu.security.core.validate.code.ValidateCodeType;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {


    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";


    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), code);
    }


    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }


    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }


    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, codeType));
    }

}