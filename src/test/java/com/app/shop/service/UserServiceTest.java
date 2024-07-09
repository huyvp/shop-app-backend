package com.app.shop.service;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.entity.Role;
import com.app.shop.entity.User;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.UserMapper;
import com.app.shop.repo.RoleRepo;
import com.app.shop.repo.UserRepo;
import com.app.shop.response.UserResponse;
import com.app.shop.service.impl.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource("/test.properties")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;
    @MockBean
    private RoleRepo roleRepo;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserMapper userMapper;

    private UserDTO userDTO;
    private User user;
    private UserResponse userResponse;
    private Role role;


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
        role = Role.builder()
                .name("USER")
                .description("User role")
                .build();
    }

    @Test
    void createUser_valid_success() {
        // GIVEN
        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(roleRepo.findById(any())).thenReturn(Optional.of(role));
        when(userMapper.toUserFromUserDTO(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any())).thenReturn(user);
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);
        // WHEN
        var response = userService.createUser(userDTO);
        // THEN
        assertThat(response.getId()).isEqualTo(1);
        assertThat(response.getFullName()).isEqualTo("fullName");
        assertThat(response.getPhoneNumber()).isEqualTo("012345678");
    }

    @Test
    void createUser_userExisted_error() {
        // GIVEN
        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        // WHEN
        ShopAppException exception = assertThrows(ShopAppException.class, () -> userService.createUser(userDTO));
        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(3001);
    }

    @Test
    void createUser_encode_password() {
        // GIVEN
        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(roleRepo.findById(any())).thenReturn(Optional.of(role));
        when(userMapper.toUserFromUserDTO(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        // WHEN
        var response = userService.createUser(userDTO);
        // THEN
        assertEquals("encodedPassword", user.getPassword());

    }

    @Test
    void createUser_not_encode_password() {
        // GIVEN
        userDTO.setFacebookAccountId(1);
        user.setPassword(userDTO.getPassword());

        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.empty());
        when(roleRepo.findById(any())).thenReturn(Optional.of(role));
        when(userMapper.toUserFromUserDTO(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        // WHEN
        var response = userService.createUser(userDTO);
        // THEN
        assertEquals("password", user.getPassword());

    }
    @Test
    @WithMockUser(username = "012345678")
    void getMyInfo_valid_success(){
        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        var response = userService.getMyInfo();

        assertEquals("012345678", response.getPhoneNumber());

    }
    @Test
    @WithMockUser(username = "012345678")
    void getMyInfo_notFoundUser_fail(){
        when(userRepo.findByPhoneNumber(anyString())).thenReturn(Optional.empty());

        var exception = assertThrows(ShopAppException.class, () -> userService.getMyInfo());

        assertEquals(exception.getErrorCode().getCode(), 3002);
    }
}
