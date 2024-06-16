package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserLoginDTO {
    @JsonProperty("phone_number")
    @NotBlank(message = "VALID_1002")
    private String phoneNumber;
    @NotBlank(message = "VALID_1002")
    private String password;
}
