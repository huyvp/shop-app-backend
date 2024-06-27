package com.app.shop.service;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.response.UserResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserDTO userDTO);

    @PostAuthorize("returnObject.phoneNumber == authentication.name")
    UserResponse updateUser(long id, UserUpdateDTO userUpdateDTO);

    void deleteUser(long id);

    @PreAuthorize("hasAuthority('MODIFY_DATA')")
    List<UserResponse> getAllUser(PageRequest pageRequest);

    @PostAuthorize("returnObject.phoneNumber == authentication.name")
    UserResponse getUserById(long id);

    UserResponse getMyInfo();

    String login(UserLoginDTO userLoginDTO);

    boolean checkTokenTest(String token);
}
