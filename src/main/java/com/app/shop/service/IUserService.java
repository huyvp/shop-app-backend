package com.app.shop.service;

import com.app.shop.dto.request.user.UserReq;
import com.app.shop.dto.request.user.UserLoginReq;
import com.app.shop.dto.request.user.UserUpdateReq;
import com.app.shop.dto.response.UserResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserReq userReq);

    @PostAuthorize("returnObject.phoneNumber == authentication.name")
    UserResponse updateUser(long id, UserUpdateReq userUpdateReq);

    void deleteUser(long id);

    @PreAuthorize("hasRole('ADMIN')")
    List<UserResponse> getAllUser(PageRequest pageRequest);

    @PostAuthorize("returnObject.phoneNumber == authentication.name")
    UserResponse getUserById(long id);

    UserResponse getMyInfo();

    String login(UserLoginReq userLoginReq);

    void logout(String token);

    String refreshToken(String token);

    boolean introspect(String token);
}
