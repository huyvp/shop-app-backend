package com.app.shop.service.impl;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.enums.Role;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.UserMapper;
import com.app.shop.models.User;
import com.app.shop.repo.RoleRepository;
import com.app.shop.repo.UserRepository;
import com.app.shop.response.UserResponse;
import com.app.shop.service.IUserService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @Override
    public UserResponse createUser(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userOptional.isPresent()) throw new ShopAppException(ErrorCode.USER_3001);
        User user = userMapper.toUserFromUserDTO(userDTO);
        user.setActive(true);
//        Role role = roleRepository.findById(userDTO.getRoleId())
//                .orElseThrow(() -> new ShopAppException(ErrorCode.ROLE_3002));
//        user.setRole(role);

        if (userDTO.getFacebookAccountId() == 0 || userDTO.getGoogleAccountId() == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public void updateUser(long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        userMapper.updateUser(user, userUpdateDTO);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        userRepository.delete(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }

    @Override
    public UserResponse getUserById(long id) {
        log.info("In method");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByPhoneNumber(currentUser)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return userMapper.toUserResponse(user);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getPassword() == null || userLoginDTO.getPhoneNumber() == null) {
            throw new ShopAppException(ErrorCode.AUTH_4002);
        }
        User user = userRepository.findByPhoneNumber(userLoginDTO.getPhoneNumber())
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        if (user != null) {
            if (passwordEncoder.matches(user.getPassword(), userLoginDTO.getPassword())) {
                throw new ShopAppException(ErrorCode.AUTH_4003);
            }
        } else {
            throw new ShopAppException(ErrorCode.AUTH_4003);
        }
        passwordEncoder.matches(user.getPassword(), userLoginDTO.getPassword());
        return generateToken(user);
    }

    @Override
    public boolean checkTokenTest(String token) {
        boolean verified;
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expireTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            verified = signedJWT.verify(verifier);
            return verified && expireTime.after(new Date());
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(User user) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getPhoneNumber())
                .issuer("nvh189")
                .issueTime(new Date())
                .claim("scope", scopeBuilder(user))
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private String scopeBuilder(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner::add);
        }
        return stringJoiner.toString();
    }
}
