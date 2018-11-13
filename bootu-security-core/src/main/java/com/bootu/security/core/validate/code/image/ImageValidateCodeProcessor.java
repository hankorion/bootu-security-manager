package com.bootu.security.core.validate.code.image;

import com.bootu.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

@Component("imageValidateCodeProcessor")
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "PNG", request.getResponse().getOutputStream());
    }
}
