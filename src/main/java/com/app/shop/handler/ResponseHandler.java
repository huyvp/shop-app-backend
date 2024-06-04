package com.app.shop.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> returnObject(HttpStatus httpStatus, Object body) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("message", "SUCCESS");
        data.put("code", httpStatus.value());
        data.put("data", body);
        return new ResponseEntity<>(data, httpStatus);
    }

    public static ResponseEntity<Object> returnMsg(HttpStatus httpStatus, String msg) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("message", "SUCCESS");
        data.put("code", httpStatus.value());
        data.put("result", msg);
        return new ResponseEntity<>(data, httpStatus);
    }

    public static ResponseEntity<Object> returnList(HttpStatus httpStatus, Object body, int total) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("message", "SUCCESS");
        data.put("code", httpStatus.value());
        data.put("total", total);
        data.put("data", body);
        return new ResponseEntity<>(data, httpStatus);
    }
    public static ResponseEntity<Object> returnBase(HttpStatus httpStatus) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("message", "SUCCESS");
        data.put("code", httpStatus.value());
        return new ResponseEntity<>(data, httpStatus);
    }
}
