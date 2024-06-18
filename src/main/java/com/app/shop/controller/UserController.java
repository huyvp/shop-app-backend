package com.app.shop.controller;

import com.app.shop.dto.UserDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseHandler.execute(null);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        userService.updateUser(id, userDTO);
        return ResponseHandler.execute(null);
    }

    @GetMapping()
    public ResponseEntity<?> getAllUser() {
        return ResponseHandler.execute(
                userService.getAllUser()
        );
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return ResponseHandler.execute(
                userService.getUserById(id)
        );
    }
}
