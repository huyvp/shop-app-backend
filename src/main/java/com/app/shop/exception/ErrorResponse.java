package com.app.shop.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.app.shop.constant.Constants.Pattern.TIME;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponse {
    @JsonFormat(pattern = TIME, timezone = "Asia/Bangkok")
    private Date timestamp;
    private int code;
    private HttpStatus status;
    private String message;
    private String path;
}
