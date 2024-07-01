package com.app.shop.controller;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.response.UserResponse;
import com.app.shop.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
        log.info("====================== Controller create user =============================");
        return ResponseHandler.execute(
                userService.createUser(userDTO)
        );
    }

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
    public ResponseEntity<?> getAllUser(@RequestParam("page") int page,
                                        @RequestParam("limit") int limit) {
        var context = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", context.getName());
        context.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority()));
        List<UserResponse> users = userService.getAllUser(
                PageRequest.of(page, limit, Sort.by("id").ascending())
        );
        return ResponseHandler.execute(users, users.size());
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
