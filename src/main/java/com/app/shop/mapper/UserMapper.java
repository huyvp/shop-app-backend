package com.app.shop.mapper;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.entity.User;
import com.app.shop.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserFromUserDTO(UserDTO userDTO);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateDTO userUpdateDTO);
}
