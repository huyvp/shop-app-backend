package com.app.shop.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginReq {
    @JsonProperty("phone_number")
    @NotBlank(message = "VALID_1002")
    String phoneNumber;
    @NotBlank(message = "VALID_1002")
    String password;
}
