package com.app.shop.controller;

import com.app.shop.dto.request.PermissionReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "permission")
public class PermissionController {
    IPermissionService permissionService;

    @PostMapping
    ResponseEntity<Object> create(@RequestBody PermissionReq permissionReq) {
        return ResponseHandler.execute(
                permissionService.create(permissionReq)
        );
    }

    @GetMapping
    ResponseEntity<Object> getAll() {
        return ResponseHandler.execute(
                permissionService.getAll()
        );
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Object> delete(@PathVariable String name) {
        permissionService.delete(name);
        return ResponseHandler.execute();
    }
}
