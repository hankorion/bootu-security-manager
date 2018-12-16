package com.bootu.security.web.dto;

import lombok.Data;

@Data
public class BasicResponse {
    private Object content;

    public BasicResponse(Object content){
        this.content = content;
    }
}
