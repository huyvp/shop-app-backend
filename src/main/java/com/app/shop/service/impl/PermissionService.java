package com.app.shop.service.impl;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.mapper.PermissionMapper;
import com.app.shop.models.Permission;
import com.app.shop.repo.PermissionRepo;
import com.app.shop.response.PermissionResponse;
import com.app.shop.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService implements IPermissionService {
    PermissionRepo permissionRepo;
    PermissionMapper permissionMapper;

    @Override
    public PermissionResponse create(PermissionDTO permissionDTO) {
        Permission permission = permissionMapper.toPermission(permissionDTO);
        permission = permissionRepo.save(permission);
        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepo.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepo.deleteById(permission);
    }
}
