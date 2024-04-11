package com.app.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAllException(Exception e){
        return new ErrorResponse(new Date(), 1000, HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException e){
        String msg = "";
        if (e.getBindingResult().hasErrors()){
            msg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return new ErrorResponse(new Date(),1004, HttpStatus.BAD_REQUEST.name(),msg);
    }

}
