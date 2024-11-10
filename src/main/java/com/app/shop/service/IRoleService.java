package com.app.shop.service;

import com.app.shop.dto.request.RoleReq;
import com.app.shop.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse create(RoleReq roleReq);

    List<RoleResponse> getAll();

    void delete(String name);
}
