package com.app.shop.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserUpdateDTO {
    @NotBlank(message = "VALID_1001")
    @JsonProperty("fullname")
    private String fullName;
    @NotBlank(message = "VALID_1002")
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
}
