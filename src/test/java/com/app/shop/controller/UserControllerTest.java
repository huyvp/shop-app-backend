package com.app.shop.controller;

import com.app.shop.dto.request.user.UserReq;
import com.app.shop.dto.response.UserResponse;
import com.app.shop.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Calendar;
import java.util.Date;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    IUserService userService;

    private UserReq userReq;
    private UserResponse userResponse;

    @BeforeEach
    void initData() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.SEPTEMBER, 18);
        Date date = calendar.getTime();
        userReq = UserReq.builder()
                .fullName("fullname")
                .phoneNumber("012345678")
                .password("password")
                .address("address")
                .dateOfBirth(date)
                .facebookAccountId(0)
                .googleAccountId(0)
                .build();
        userResponse = UserResponse.builder()
                .id(1)
                .fullName("fullname")
                .phoneNumber("012345678")
                .address("address")
                .dateOfBirth(date)
                .build();
    }

    @Test
    void createUser_validReq_success() throws Exception {
        log.info("Create user valid request success");
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(userReq);
        Mockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(userResponse);
        // WHEN

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content)
        );

//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        // THEN
    }
}
