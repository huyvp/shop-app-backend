package com.app.shop.controller;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    IUserService userService;

    @PostMapping(value = "login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userDTO) {
        return ResponseHandler.execute(
                userService.login(userDTO)
        );
    }

    @PostMapping("introspect")
    public ResponseEntity<?> introspect(@RequestParam("token") String token) {
        return ResponseHandler.execute(
                userService.introspect(token)
        );
    }

    @PostMapping(value = "logout")
    public ResponseEntity<?> logout(@RequestParam("token") String token) {
        userService.logout(token);
        return ResponseHandler.execute(null);
    }

    @PostMapping(value = "refresh")
    public ResponseEntity<?> refresh(@RequestParam("token") String token) {
        return ResponseHandler.execute(
                userService.refreshToken(token)
        );
    }
}
