package com.app.shop.service;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);

    void updateUser(long id, UserUpdateDTO userUpdateDTO);

    void deleteUser(long id);

    List<UserResponse> getAllUser();

    UserResponse getUserById(long id);

    String login(UserLoginDTO userLoginDTO);

    boolean checkTokenTest(String token);
}
