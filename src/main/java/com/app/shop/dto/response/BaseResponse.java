package com.app.shop.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.app.shop.constant.Constants.Pattern.DATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseResponse {
    @JsonProperty("created_at")
    @JsonFormat(pattern = DATE, timezone = "Asia/Bangkok")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    @JsonFormat(pattern = DATE, timezone = "Asia/Bangkok")
    private LocalDateTime updatedAt;
}
