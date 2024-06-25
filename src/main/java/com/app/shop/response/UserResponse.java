package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

import static com.app.shop.constant.Constants.Pattern.DOB;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    @JsonProperty("fullname")
    String fullName;
    @JsonProperty("phone_number")
    String phoneNumber;
    String address;
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = DOB)
    Date dateOfBirth;
    Set<String> roles;
}
