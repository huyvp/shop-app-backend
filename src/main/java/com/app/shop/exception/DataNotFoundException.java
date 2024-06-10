package com.app.shop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode ;
    public DataNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
