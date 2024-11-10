package com.app.shop.dto.request.user;

import com.app.shop.validator.DateOfBirth;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateReq {
    @NotBlank(message = "VALID_1001")
    @JsonProperty("fullname")
    String fullName;
    @NotBlank(message = "VALID_1002")
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    @DateOfBirth(min = 20, message = "VALID_1013")
    @JsonProperty("date_of_birth")
    Date dateOfBirth;
    List<String> roles;
}
