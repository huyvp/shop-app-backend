package com.app.shop.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopAppException extends RuntimeException {
    private ErrorCode errorCode;

    public ShopAppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
