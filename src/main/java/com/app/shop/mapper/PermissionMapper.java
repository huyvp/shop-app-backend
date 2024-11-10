package com.app.shop.mapper;

import com.app.shop.dto.request.PermissionReq;
import com.app.shop.entity.Permission;
import com.app.shop.dto.response.PermissionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionReq permissionReq);

    PermissionResponse toPermissionResponse(Permission permission);
}
