package com.app.shop.controller;

import com.app.shop.dto.PermissionDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IPermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
    IPermissionService permissionService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody PermissionDTO permissionDTO) {
        return ResponseHandler.execute(
                permissionService.create(permissionDTO)
        );
    }

    @GetMapping
    ResponseEntity<?> getAll() {
        return ResponseHandler.execute(
                permissionService.getAll()
        );
    }

    @DeleteMapping("/{permission}")
    ResponseEntity<?> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ResponseHandler.execute(null);
    }
}
