package com.app.shop.handler;

import com.app.shop.exception.DataNotFoundException;
import com.app.shop.exception.FileFormatNotSupportException;
import com.app.shop.exception.FileSizeException;
import com.app.shop.exception.ShopException;
import com.app.shop.response.ErrorResponse;
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
    public ErrorResponse handleAllException(Exception ex) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler({ShopException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleShopException(ShopException ex) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBindException(BindException ex) {
        String msg = "";
        if (ex.getBindingResult().hasErrors()) {
            msg = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        }
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST)
                .message(msg)
                .build();
    }

    @ExceptionHandler(FileSizeException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public ErrorResponse handleFileSizeException(FileSizeException ex) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.PAYLOAD_TOO_LARGE.value())
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(FileFormatNotSupportException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ErrorResponse handleFileFormatNotSupportException(FileFormatNotSupportException ex) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDataNotFoundException(DataNotFoundException ex) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
    }
}
