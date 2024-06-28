package com.app.shop.service.impl;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.PermissionMapper;
import com.app.shop.entity.Permission;
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
        if (permissionRepo.findById(permissionDTO.getName()).isPresent())
            throw new ShopAppException(ErrorCode.PERMISSION_3001);

        Permission permission = permissionMapper.toPermission(permissionDTO);
        permission = permissionRepo.save(permission);

        return permissionMapper.toPermissionResponse(permission);
    }

    @Override
    public List<PermissionResponse> getAll() {
        var permissions = permissionRepo.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    @Override
    public void delete(String permission) {
        permissionRepo.findById(permission)
                .orElseThrow(() -> new ShopAppException(ErrorCode.PERMISSION_3002));
        permissionRepo.deleteById(permission);
    }
}
