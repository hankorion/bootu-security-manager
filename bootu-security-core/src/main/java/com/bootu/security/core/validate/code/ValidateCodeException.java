package com.bootu.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 124366780020895472L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
