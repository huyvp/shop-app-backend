package com.app.shop.exception;

public class ShopException extends RuntimeException {
    private static final long serialVersionUID = -4432758129813920577L;
    public ShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
