package com.app.shop.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDTO {
    @NotBlank(message = "VALID_1001")
    @JsonProperty("fullname")
    String fullName;
    @NotBlank(message = "VALID_1002")
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    @JsonProperty("date_of_birth")
    Date dateOfBirth;
    List<String> roles;
}
