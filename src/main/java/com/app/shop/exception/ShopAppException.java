package com.app.shop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ShopAppException extends RuntimeException {
    private final ErrorCode errorCode;

    public ShopAppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
