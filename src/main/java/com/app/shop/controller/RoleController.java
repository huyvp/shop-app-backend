package com.app.shop.controller;

import com.app.shop.dto.request.RoleReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IRoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    IRoleService roleService;

    @PostMapping
    ResponseEntity<Object> create(@RequestBody RoleReq roleReq) {
        return ResponseHandler.execute(
                roleService.create(roleReq)
        );
    }

    @GetMapping
    ResponseEntity<Object> getAll() {
        return ResponseHandler.execute(
                roleService.getAll()
        );
    }

    @DeleteMapping("/{name}")
    ResponseEntity<Object> delete(@PathVariable String name) {
        roleService.delete(name);
        return ResponseHandler.execute();
    }
}
