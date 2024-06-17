package com.app.shop.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.app.shop.constant.Constants.Pattern.DOB;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {
    private int id;
    @JsonProperty("fullname")
    private String fullName;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;
    @JsonProperty("date_of_birth")
    @JsonFormat(pattern = DOB)
    private Date dateOfBirth;
}
