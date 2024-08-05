package com.app.shop.mapper;

import com.app.shop.dto.RoleDTO;
import com.app.shop.entity.Role;
import com.app.shop.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleDTO request);

    RoleResponse toRoleResponse(Role role);
}
