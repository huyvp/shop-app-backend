package com.app.shop.mapper;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.models.Permission;
import com.app.shop.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionDTO permissionDTO);

    PermissionResponse toPermissionResponse(Permission permission);
}
