package com.bootu.security.core.validate.code.impl;

import com.bootu.security.core.validate.code.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@Slf4j
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Autowired
    private ValidateCodeRepository validateCodeRepository;


    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getValidateCodeType(request).toString().toLowerCase();
        String generatorName = type + ValidateCodeGenerator.class.getSimpleName();
        log.info("generate validate code for type=[{}], generatorName=[{}]", type, generatorName);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(generatorName);
        if (validateCodeGenerator == null) {
            throw new ValidateCodeException("Validate Code Generator " + generatorName + " not exits");
        }
        return (C) validateCodeGenerator.generateCode(request);
    }

    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType(request));
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    private ValidateCodeType getValidateCodeType(ServletWebRequest request) {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }


    @SuppressWarnings("unchecked")
    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType codeType = getValidateCodeType(request);

        C codeInSession = (C) validateCodeRepository.get(request, codeType);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    codeType.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("Failed to generate validate code");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(codeType + " validate code cannot be empty");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(codeType + " validate code not exists");
        }

        if (codeInSession.isExpired()) {
            validateCodeRepository.remove(request, codeType);
            throw new ValidateCodeException(codeType + " validate code expired");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(codeType + " validate code not match");
        }

        validateCodeRepository.remove(request, codeType);
    }
}
