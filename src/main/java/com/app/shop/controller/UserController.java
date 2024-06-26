package com.app.shop.controller;

import com.app.shop.dto.user.UserUpdateDTO;
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
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService userService;

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseHandler.execute(null);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,
                                        @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        return ResponseHandler.execute(
                userService.updateUser(id, userUpdateDTO)
        );
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

    @GetMapping(value = "/myInfo")
    public ResponseEntity<?> getMyInfo() {
        return ResponseHandler.execute(
                userService.getMyInfo()
        );
    }
}
