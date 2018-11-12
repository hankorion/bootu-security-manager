package com.bootu.security.core.validate.code;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;


@Data
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireAfter) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfter);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
