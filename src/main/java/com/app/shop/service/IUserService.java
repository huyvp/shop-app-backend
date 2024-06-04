package com.app.shop.service;

import com.app.shop.dto.UserDTO;
import com.app.shop.models.User;

public interface IUserService {
    User createUser(UserDTO userDTO);

    String login(String phoneNumber, String password);
}
