package com.app.shop.controller;

import com.app.shop.dto.request.user.UserReq;
import com.app.shop.dto.request.user.UserUpdateReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.dto.response.UserResponse;
import com.app.shop.service.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Tag(name = "user")
public class UserController {
    IUserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserReq userReq) {
        return ResponseHandler.execute(
                userService.createUser(userReq)
        );
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseHandler.execute();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Object> updateUser(@PathVariable int id,
                                             @RequestBody @Valid UserUpdateReq userUpdateReq) {
        return ResponseHandler.execute(
                userService.updateUser(id, userUpdateReq)
        );
    }

    @GetMapping()
    public ResponseEntity<Object> getAllUser(@RequestParam("page") int page,
                                             @RequestParam("limit") int limit) {
        List<UserResponse> users = userService.getAllUser(
                PageRequest.of(page, limit, Sort.by("id").ascending())
        );
        return ResponseHandler.execute(users, users.size());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        return ResponseHandler.execute(
                userService.getUserById(id)
        );
    }

    @GetMapping(value = "/myInfo")
    public ResponseEntity<Object> getMyInfo() {
        return ResponseHandler.execute(
                userService.getMyInfo()
        );
    }
}
