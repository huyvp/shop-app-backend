package com.app.shop.handler;

import com.app.shop.entity.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseHandler {
    public static ResponseEntity<Object> returnObject(HttpStatus httpStatus, Object body) {
        SuccessResponse successResponse = SuccessResponse.builder()
                .timestamp(new Date())
                .code(httpStatus.value())
                .status(httpStatus)
                .message("Success")
                .data(body)
                .build();
        return new ResponseEntity<>(successResponse, httpStatus);
    }
    public static ResponseEntity<Object> returnMsg(HttpStatus httpStatus, String msg) {
        SuccessResponse successResponse = SuccessResponse.builder()
                .timestamp(new Date())
                .code(httpStatus.value())
                .status(httpStatus)
                .message(msg)
                .build();
        return new ResponseEntity<>(successResponse, httpStatus);
    }
}
