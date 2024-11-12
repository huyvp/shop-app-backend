package com.app.shop.controller;

import com.app.shop.dto.request.user.UserLoginReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "auth")
public class AuthController {
    IUserService userService;

    @PostMapping(value = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginReq userDTO) {
        return ResponseHandler.execute(
                userService.login(userDTO)
        );
    }

    @PostMapping("introspect")
    public ResponseEntity<Object> introspect(@RequestParam("token") String token) {
        return ResponseHandler.execute(
                userService.introspect(token)
        );
    }

    @PostMapping(value = "logout")
    public ResponseEntity<Object> logout(@RequestParam("token") String token) {
        userService.logout(token);
        return ResponseHandler.execute();
    }

    @PostMapping(value = "refresh")
    public ResponseEntity<Object> refresh(@RequestParam("token") String token) {
        return ResponseHandler.execute(
                userService.refreshToken(token)
        );
    }
}
