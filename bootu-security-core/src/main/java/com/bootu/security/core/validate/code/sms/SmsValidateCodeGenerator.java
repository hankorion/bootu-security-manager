package com.bootu.security.core.validate.code.sms;

import com.bootu.security.core.enums.ImagePropertiesEnum;
import com.bootu.security.core.properties.SecurityProperties;
import com.bootu.security.core.validate.code.ValidateCode;
import com.bootu.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Random;

@Component("smsValidateCodeGenerator")
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generateCode(ServletWebRequest request) {
        // image code count
        int codeCount = ServletRequestUtils.getIntParameter(request.getRequest(), ImagePropertiesEnum.codeCount.toString(), securityProperties.getCode().getSms().getCodeCount());
        // image code expire after x seconds
        int expireAfter = ServletRequestUtils.getIntParameter(request.getRequest(), ImagePropertiesEnum.expireAfter.toString(), securityProperties.getCode().getSms().getExpireAfter());

        // 验证码
        String code = null;

        // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        // 生成随机数
        Random random = new Random();
        // randomCode记录随机产生的验证码
        StringBuffer randomCode = new StringBuffer();
        // 随机产生codeCount个字符的验证码。
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
        return new ValidateCode(code, expireAfter);
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
