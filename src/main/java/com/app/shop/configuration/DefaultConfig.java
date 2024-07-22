package com.app.shop.configuration;

import com.app.shop.entity.Role;
import com.app.shop.entity.User;
import com.app.shop.repo.RoleRepo;
import com.app.shop.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;

import static com.app.shop.constant.Constants.ADMIN_ACCOUNT.ADMIN_PASSWORD;
import static com.app.shop.constant.Constants.ADMIN_ACCOUNT.ADMIN_USERNAME;
import static com.app.shop.constant.Constants.PreDefineRole.ROLE_ADMIN;
import static com.app.shop.constant.Constants.PreDefineRole.ROLE_USER;

@Configuration
@Slf4j
public class DefaultConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value = "datasource.driver-class-name",
            havingValue = "org.postgresql.Driver"
    )
    ApplicationRunner applicationRunner(UserRepo userRepo, RoleRepo roleRepo) {
        log.info("Initializing application start .......");
        return args -> {
            if (userRepo.findByPhoneNumber(ADMIN_USERNAME).isEmpty()) {
                roleRepo.save(Role.builder()
                        .name(ROLE_USER)
                        .description("user role")
                        .build());
                Role adminRole = roleRepo.save(Role.builder()
                        .name(ROLE_ADMIN)
                        .description("Admin role")
                        .build());
                HashSet<Role> roles = new HashSet<>();
                roles.add(adminRole);

                User user = User.builder()
                        .phoneNumber(ADMIN_USERNAME)
                        .fullName(ADMIN_USERNAME)
                        .active(true)
                        .dateOfBirth(new Date())
                        .address("address")
                        .googleAccountId(0)
                        .facebookAccountId(0)
                        .password(passwordEncoder().encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();
                userRepo.save(user);
                log.warn("Admin user have been created with default password is \"admin\" ");
            }
            log.info("Initializing application complete");
        };
    }
}
