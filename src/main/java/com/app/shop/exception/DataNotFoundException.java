package com.app.shop.exception;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -5064474453848143216L;
    public DataNotFoundException(String message) {
        super(message);
    }
}
