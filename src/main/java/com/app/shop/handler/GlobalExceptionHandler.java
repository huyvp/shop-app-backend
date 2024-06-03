package com.app.shop.handler;

import com.app.shop.entity.ErrorResponse;
import com.app.shop.exception.DataNotFoundException;
import com.app.shop.exception.FileFormatNotSupportException;
import com.app.shop.exception.FileSizeException;
import jakarta.servlet.http.HttpServletRequest;
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
    public ErrorResponse handleAllException(Exception ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException ex, HttpServletRequest request) {
        String msg = "";
        if (ex.getBindingResult().hasErrors()) {
            msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(msg)
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(FileSizeException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ErrorResponse handleFileSizeException(FileSizeException ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(FileFormatNotSupportException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorResponse handleFileFormatNotSupportException(FileFormatNotSupportException ex, HttpServletRequest request) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFoundException(DataNotFoundException ex, HttpServletRequest request){
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }
}