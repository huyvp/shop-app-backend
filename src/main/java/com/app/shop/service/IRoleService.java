package com.app.shop.service;

import com.app.shop.dto.role.RoleDTO;
import com.app.shop.dto.role.RoleUpdateDTO;
import com.app.shop.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleDTO roleDTO);

    List<RoleResponse> getAll();

    RoleResponse update(String role, RoleUpdateDTO roleUpdateDTO);

    void delete(String name);
}
