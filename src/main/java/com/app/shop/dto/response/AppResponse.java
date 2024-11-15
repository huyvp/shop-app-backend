package com.app.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.app.shop.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse<T> {
    @Builder.Default
    @JsonFormat(pattern = TIME, timezone = "Asia/Bangkok")
    private LocalDateTime timestamp = LocalDateTime.now();
    private int code;
    private HttpStatus status;
    private String message;
    private Integer totals;
    private T result;
}
