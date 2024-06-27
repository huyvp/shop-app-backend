package com.app.shop.handler;

import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.response.AppResponse;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<AppResponse<?>> handleRestException(Exception ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .code(ErrorCode.UNCATEGORIZED.getCode())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(appResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ShopAppException.class)
    ResponseEntity<AppResponse<?>> handleShopException(ShopAppException ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .code(ex.getErrorCode().getCode())
                .status(ex.getErrorCode().getHttpStatus())
                .message(ex.getErrorCode().getMessage())
                .build();
        return ResponseEntity
                .status(ex.getErrorCode().getHttpStatus())
                .body(appResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ResponseEntity<AppResponse<?>> handleAccessDeniedException() {
        AppResponse<?> appResponse = AppResponse.builder()
                .code(ErrorCode.AUTH_4000.getCode())
                .status(ErrorCode.AUTH_4000.getHttpStatus())
                .message(ErrorCode.AUTH_4000.getMessage())
                .build();
        return ResponseEntity
                .status(ErrorCode.AUTH_4000.getHttpStatus())
                .body(appResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<AppResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String enumKey = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(enumKey);

        ConstraintViolation<?> constraintViolation = ex.getBindingResult()
                .getAllErrors().get(0)
                .unwrap(ConstraintViolation.class);

        Map<String, Object> attributes = constraintViolation.getConstraintDescriptor().getAttributes();
        log.info(attributes.toString());

        AppResponse<?> appResponse = AppResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getHttpStatus())
                .message(mapAttributes(errorCode.getMessage(), attributes))
                .build();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(appResponse);
    }

    private String mapAttributes(String msg, Map<String, Object> attributes) {
        if (Objects.nonNull(attributes)) {
            String min = String.valueOf(attributes.get("min"));
            return msg.replace("{min}", min);
        }else {
            return msg;
        }
    }
}
