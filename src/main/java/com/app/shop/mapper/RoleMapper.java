package com.app.shop.mapper;

import com.app.shop.dto.RoleDTO;
import com.app.shop.models.Role;
import com.app.shop.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleDTO request);

    RoleResponse toRoleResponse(Role role);
}
