package com.app.shop.service;

import com.app.shop.dto.UserDTO;
import com.app.shop.dto.UserLoginDTO;
import com.app.shop.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);

    void updateUser(long id, UserDTO userDTO);

    void deleteUser(long id);

    List<UserResponse> getAllUser();

    UserResponse getUserById(long id);

    String login(UserLoginDTO userLoginDTO);

    boolean checkTokenTest(String token);
}
