package com.app.shop.handler;

import com.app.shop.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseHandler {
    public static ResponseEntity<Object> execute(Object result, int... totals) {
        AppResponse<Object> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(2000)
                .message("success")
                .status(HttpStatus.OK)
                .result(result)
                .build();
        if (totals.length != 0) appResponse.setTotals(totals[0]);
        else appResponse.setTotals(null);
        return ResponseEntity.ok(appResponse);
    }
    public static ResponseEntity<Object> execute() {
        return execute(null);
    }
}
