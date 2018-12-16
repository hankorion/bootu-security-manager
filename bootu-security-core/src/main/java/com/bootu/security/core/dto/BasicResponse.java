package com.bootu.security.core.dto;

import lombok.Data;

@Data
public class BasicResponse {
    private Object content;

    public BasicResponse(Object content){
        this.content = content;
    }
}
