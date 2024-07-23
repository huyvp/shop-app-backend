package com.app.shop.service.impl;

import com.app.shop.dto.role.RoleDTO;
import com.app.shop.dto.role.RoleUpdateDTO;
import com.app.shop.entity.Permission;
import com.app.shop.entity.Role;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.RoleMapper;
import com.app.shop.repo.PermissionRepo;
import com.app.shop.repo.RoleRepo;
import com.app.shop.response.RoleResponse;
import com.app.shop.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepo roleRepo;
    PermissionRepo permissionRepo;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleDTO roleDTO) {
        if (roleRepo.findById(roleDTO.getName()).isPresent())
            throw new ShopAppException(ErrorCode.ROLE_3001);
        List<Permission> permissions = permissionRepo.findAllById(roleDTO.getPermissions());
        Role role = roleMapper.toRole(roleDTO);
        role.setPermissions(new HashSet<>(permissions));

        return roleMapper.toRoleResponse(roleRepo.save(role));
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepo.findAll().stream()
                .map(roleMapper::toRoleResponse)
                .toList();
    }

    @Override
    public RoleResponse update(String role, RoleUpdateDTO roleUpdateDTO) {
        Role roleToUpdate = roleRepo.findById(role)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ROLE_3002));
        Set<Permission> permissions = roleToUpdate.getPermissions();
        for (Permission permission : permissions) {
            if (permissionRepo.findById(permission.getName()).isPresent())
                throw new ShopAppException(ErrorCode.PERMISSION_3002);
        }
        roleMapper.updateRole(roleUpdateDTO, roleToUpdate);
        Role updatedRole = roleRepo.save(roleToUpdate);
        return roleMapper.toRoleResponse(updatedRole);
    }

    @Override
    public void delete(String name) {
        Role roleToDelete = roleRepo.findById(name)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ROLE_3002));
        roleRepo.delete(roleToDelete);
    }
}
