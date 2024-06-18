package com.app.shop.controller;

import com.app.shop.dto.UserDTO;
import com.app.shop.dto.UserLoginDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    IUserService userService;

    @PostMapping(value = "register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseHandler.execute(userService.createUser(userDTO));
    }

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userDTO) {
        return ResponseHandler.execute(userService.login(userDTO));
    }

    @PostMapping("introspect")
    public ResponseEntity<?> introspect(@RequestParam("token") String token) {
        return ResponseHandler.execute(userService.checkTokenTest(token));
    }
}
