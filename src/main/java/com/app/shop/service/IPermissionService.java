package com.app.shop.service;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionDTO permissionDTO);

    List<PermissionResponse> getAll();

    void delete(String permission);
}
