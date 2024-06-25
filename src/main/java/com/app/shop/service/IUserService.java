package com.app.shop.service;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.response.UserResponse;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);

    void updateUser(long id, UserUpdateDTO userUpdateDTO);

    void deleteUser(long id);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserResponse> getAllUser();
    @PostAuthorize("returnObject.phoneNumber == authentication.name")
    UserResponse getUserById(long id);

    UserResponse getMyInfo();

    String login(UserLoginDTO userLoginDTO);

    boolean checkTokenTest(String token);
}
