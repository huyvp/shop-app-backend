package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.app.shop.constant.Constants.Pattern.DATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
public class BaseResponse implements Serializable {
    @JsonProperty("created_at")
    @JsonFormat(pattern = DATE, timezone = "Asia/Bangkok")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    @JsonFormat(pattern = DATE, timezone = "Asia/Bangkok")
    private LocalDateTime updatedAt;
}
