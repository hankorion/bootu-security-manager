package com.bootu.security.core.validate.code;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ValidateCode {


    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireAfter) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfter);
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
