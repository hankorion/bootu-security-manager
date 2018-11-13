package com.bootu.security.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender{
    @Override
    public void send(String mobileNo, String code) {
        log.info("Send SMS message [{}] to [{}]",code, mobileNo);
    }
}
