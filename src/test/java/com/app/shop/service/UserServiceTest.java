package com.app.shop.service;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.entity.User;
import com.app.shop.exception.ShopAppException;
import com.app.shop.repo.UserRepo;
import com.app.shop.response.UserResponse;
import com.app.shop.service.impl.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    private UserDTO userDTO;
    private User user;
    private UserResponse userResponse;


    @BeforeEach
    public void setUp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.SEPTEMBER, 18);
        Date date = calendar.getTime();
        userDTO = UserDTO.builder()
                .fullName("fullName")
                .phoneNumber("012345678")
                .password("password")
                .address("address")
                .dateOfBirth(date)
                .facebookAccountId(0)
                .googleAccountId(0)
                .build();
        userResponse = UserResponse.builder()
                .id(1)
                .fullName("fullName")
                .phoneNumber("012345678")
                .address("address")
                .dateOfBirth(date)
                .build();
        user = User.builder()
                .id(1L)
                .fullName("fullName")
                .phoneNumber("012345678")
                .address("address")
                .dateOfBirth(date)
                .build();
    }

    @Test
    void createUser_validReq() {
        // GIVEN
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        // WHEN
        UserResponse response = userService.createUser(userDTO);
        // THEN
        Assertions.assertThat(response.getId()).isEqualTo(1);
        Assertions.assertThat(response.getFullName()).isEqualTo("fullName");
    }

    @Test
    void createUser_userExisted() {
        // GIVEN
        Mockito.when(userRepo.findByPhoneNumber(Mockito.anyString())).thenReturn(Optional.of(user));
        // WHEN
        ShopAppException exception = assertThrows(ShopAppException.class, () -> userService.createUser(userDTO));
        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(3001);
    }

}
