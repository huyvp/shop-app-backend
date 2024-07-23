package com.app.shop.mapper;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.dto.role.RoleDTO;
import com.app.shop.dto.role.RoleUpdateDTO;
import com.app.shop.entity.Permission;
import com.app.shop.entity.Role;
import com.app.shop.response.RoleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleDTO request);

    RoleResponse toRoleResponse(Role role);

    @Mapping(target = "permissions", ignore = true)
    void updateRole(RoleUpdateDTO roleUpdateDTO, @MappingTarget Role role);

    @Named("updateRole")
    default Set<Permission> updateRole(Set<PermissionDTO> permissions) {
        return permissions.stream().map(item -> Permission.builder()
                .name(item.getName())
                .description(item.getDescription())
                .build()).collect(Collectors.toSet());
    }
}
