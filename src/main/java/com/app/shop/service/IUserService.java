package com.app.shop.service;

import com.app.shop.dto.UserDTO;
import com.app.shop.dto.UserLoginDTO;
import com.app.shop.models.User;
import com.app.shop.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);

    String login(UserLoginDTO userLoginDTO);
}
