package com.app.shop.service.impl;

import com.app.shop.dto.user.UserDTO;
import com.app.shop.dto.user.UserLoginDTO;
import com.app.shop.dto.user.UserUpdateDTO;
import com.app.shop.entity.InvalidatedToken;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.UserMapper;
import com.app.shop.entity.Role;
import com.app.shop.entity.User;
import com.app.shop.repo.InvalidatedTokenRepo;
import com.app.shop.repo.RoleRepo;
import com.app.shop.repo.UserRepo;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.app.shop.constant.Constants.PreDefineRole.ROLE_USER;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepo userRepo;
    RoleRepo roleRepo;
    InvalidatedTokenRepo invalidatedTokenRepo;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;
    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;
    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESH_ABLE_DURATION;


    @Override
    public UserResponse createUser(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();

        if (userRepo.findByPhoneNumber(phoneNumber).isPresent())
            throw new ShopAppException(ErrorCode.USER_3001);

        User user = userMapper.toUserFromUserDTO(userDTO);
        user.setActive(true);

        HashSet<Role> roles = new HashSet<>();
        roleRepo.findById(ROLE_USER).ifPresent(roles::add);
        user.setRoles(roles);

        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User savedUser = userRepo.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        userMapper.updateUser(user, userUpdateDTO);

        List<Role> roles = roleRepo.findAllById(userUpdateDTO.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepo.save(user));
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        userRepo.delete(user);
    }

    @Override
    public List<UserResponse> getAllUser(PageRequest pageRequest) {
        Page<User> users = userRepo.findAll(pageRequest);
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }

    @Override
    public UserResponse getUserById(long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByPhoneNumber(currentUser)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return userMapper.toUserResponse(user);
    }

    @Override
    public String login(UserLoginDTO userLoginDTO) {
        log.info("SIGNER_KEY: {}", SIGNER_KEY);
        if (userLoginDTO.getPassword() == null || userLoginDTO.getPhoneNumber() == null) {
            throw new ShopAppException(ErrorCode.AUTH_4002);
        }
        User user = userRepo.findByPhoneNumber(userLoginDTO.getPhoneNumber())
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
    public boolean introspect(String token) {
        try {
            verifyToken(token, false);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void logout(String token) {
        try {
            SignedJWT signedToken = verifyToken(token, true);
            String tokenId = signedToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();
            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(tokenId)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepo.save(invalidatedToken);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String refreshToken(String token) {
        SignedJWT signedToken = verifyToken(token, true);
        try {
            String tokenId = signedToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(tokenId)
                    .expiryTime(expiryTime)
                    .build();
            invalidatedTokenRepo.save(invalidatedToken);
            String phoneNumber = signedToken.getJWTClaimsSet().getSubject();

            User user = userRepo.findByPhoneNumber(phoneNumber)
                    .orElseThrow(() -> new ShopAppException(ErrorCode.AUTH_4001));
            return generateToken(user);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private SignedJWT verifyToken(String token, boolean isRefresh) {
        boolean verified;
        try {
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
            SignedJWT signedJWT = SignedJWT.parse(token);

            Date expireTime = isRefresh
                    ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(REFRESH_ABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                    : signedJWT.getJWTClaimsSet().getExpirationTime();
            verified = signedJWT.verify(verifier);

            if (!verified && expireTime.after(new Date()))
                throw new ShopAppException(ErrorCode.AUTH_4001);
            if (invalidatedTokenRepo.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
                throw new ShopAppException(ErrorCode.AUTH_4001);
            return signedJWT;
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
                .claim("scope", scopeBuilder(user.getRoles()))
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
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

    private String scopeBuilder(Set<Role> roles) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!roles.isEmpty()) {
            roles.forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!role.getPermissions().isEmpty()) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }
}
