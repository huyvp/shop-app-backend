package com.app.shop.service;

import com.app.shop.dto.RoleDTO;
import com.app.shop.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleDTO roleDTO);

    List<RoleResponse> getAll();

    void delete(String name);
}
