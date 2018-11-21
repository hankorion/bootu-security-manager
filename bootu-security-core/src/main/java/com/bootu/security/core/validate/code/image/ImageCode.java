package com.bootu.security.core.validate.code.image;

import com.bootu.security.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode extends ValidateCode {

    private static final long serialVersionUID = -3857073768892403135L;

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireAfter) {
        super(code,expireAfter);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }

}
