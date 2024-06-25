package com.app.shop.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    @NotBlank(message = "VALID_1001")
    @JsonProperty("fullname")
    String fullName;
    @NotBlank(message = "VALID_1002")
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    @NotBlank(message = "VALID_1003")
    String password;
    @JsonProperty("date_of_birth")
    Date dateOfBirth;
    @JsonProperty("facebook_account_id")
    int facebookAccountId;
    @JsonProperty("google_account_id")
    int googleAccountId;
    @JsonProperty("role_id")
    Long roleId;
}
