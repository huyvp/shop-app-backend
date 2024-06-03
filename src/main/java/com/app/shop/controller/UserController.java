package com.app.shop.controller;

import com.app.shop.dto.UserDTO;
import com.app.shop.dto.UserLoginDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO userDTO) {
        return ResponseHandler.returnObject(HttpStatus.CREATED, userService.createUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDTO userDTO) {
        return "User login token";
    }
}
