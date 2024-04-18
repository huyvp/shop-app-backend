package com.app.shop.controller;

import com.app.shop.dto.UserDTO;
import com.app.shop.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    @PostMapping("/register")
    public String createUser(@Valid @RequestBody UserDTO userDTO){
        return "Create user";
    }
    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDTO userDTO){
        return "User login token";
    }
}
