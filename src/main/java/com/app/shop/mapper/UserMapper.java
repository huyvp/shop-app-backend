package com.app.shop.mapper;

import com.app.shop.dto.request.user.UserReq;
import com.app.shop.dto.request.user.UserUpdateReq;
import com.app.shop.entity.User;
import com.app.shop.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserFromUserDTO(UserReq userReq);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateReq userUpdateReq);
}
