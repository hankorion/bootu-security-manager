package com.bootu.security.core.validate.code;

import com.bootu.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }


//    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
//
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//
//    @Autowired
//    private ValidateCodeGenerator imageValidateCodeGenerator;
//
//    @Autowired
//    private ValidateCodeGenerator smsValidateCodeGenerator;
//
//    @Autowired
//    private SmsCodeSender smsCodeSender;



//
//    @GetMapping("/code/image")
//    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        ImageCode imageCode = (ImageCode) imageValidateCodeGenerator.generateCode(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//
//        ImageIO.write(imageCode.getImage(), "PNG", response.getOutputStream());
//    }
//
//    @GetMapping("/code/sms")
//    public void createSMSCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
//
//        ValidateCode smsCode = smsValidateCodeGenerator.generateCode(new ServletWebRequest(request));
//        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//        String mobileNo = ServletRequestUtils.getRequiredStringParameter(request,"mobileNo");
//        smsCodeSender.send(mobileNo,smsCode.getCode());
//    }

}
