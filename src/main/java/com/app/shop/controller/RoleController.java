package com.app.shop.controller;

import com.app.shop.dto.RoleDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    IRoleService roleService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody RoleDTO roleDTO) {
        return ResponseHandler.execute(
                roleService.create(roleDTO)
        );
    }

    @GetMapping
    ResponseEntity<?> getAll() {
        return ResponseHandler.execute(
                roleService.getAll()
        );
    }

    @DeleteMapping("/{role}")
    ResponseEntity<?> delete(@PathVariable String role) {
        roleService.delete(role);
        return ResponseHandler.execute(null);
    }
}
