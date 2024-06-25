package com.app.shop.mapper;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.models.User;
import com.app.shop.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    User toUserFromUserDTO(UserDTO userDTO);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateDTO userUpdateDTO);
}
