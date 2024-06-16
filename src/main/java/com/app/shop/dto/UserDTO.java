package com.app.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {
    @NotBlank(message = "VALID_1001")
    @JsonProperty("fullname")
    private String fullName;
    @NotBlank(message = "VALID_1002")
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @NotBlank(message = "VALID_1003")
    private String password;
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
    @JsonProperty("facebook_account_id")
    private int facebookAccountId;
    @JsonProperty("google_account_id")
    private int googleAccountId;
    @JsonProperty("role_id")
    private Long roleId;
}
