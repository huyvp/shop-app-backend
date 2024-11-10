package com.app.shop.service;

import com.app.shop.dto.request.PermissionReq;
import com.app.shop.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionReq permissionReq);

    List<PermissionResponse> getAll();

    void delete(String name);
}
