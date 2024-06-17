package com.app.shop.mapper;

import com.app.shop.dto.UserDTO;
import com.app.shop.models.User;
import com.app.shop.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserResponse toUserResponse(User user);
}
