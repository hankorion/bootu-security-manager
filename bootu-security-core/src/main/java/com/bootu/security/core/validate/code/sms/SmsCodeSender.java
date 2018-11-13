package com.bootu.security.core.validate.code.sms;

public interface SmsCodeSender {
    void send(String mobileNo, String code);
}
