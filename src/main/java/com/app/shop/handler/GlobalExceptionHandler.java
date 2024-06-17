package com.app.shop.handler;

import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<AppResponse<?>> handleRestException(Exception ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(ErrorCode.UNCATEGORIZED.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ShopAppException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<AppResponse<?>> handleShopException(ShopAppException ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(ex.getErrorCode().getCode())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<AppResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String enumKey = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(errorCode.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }
}
