package com.app.shop.service.impl;

import com.app.shop.dto.request.RoleReq;
import com.app.shop.entity.Permission;
import com.app.shop.entity.Role;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.RoleMapper;
import com.app.shop.repo.PermissionRepo;
import com.app.shop.repo.RoleRepo;
import com.app.shop.dto.response.RoleResponse;
import com.app.shop.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepo roleRepo;
    PermissionRepo permissionRepo;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleReq roleReq) {
        if (roleRepo.findById(roleReq.getName()).isPresent())
            throw new ShopAppException(ErrorCode.ROLE_3001);
        List<Permission> permissions = permissionRepo.findAllById(roleReq.getPermissions());
        Role role = roleMapper.toRole(roleReq);
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
    public void delete(String name) {
        Role roleToDelete = roleRepo.findById(name)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ROLE_3002));
        roleRepo.delete(roleToDelete);
    }
}
