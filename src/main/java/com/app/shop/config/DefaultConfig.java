package com.app.shop.config;

import com.app.shop.enums.Role;
import com.app.shop.models.User;
import com.app.shop.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@Slf4j
public class DefaultConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByPhoneNumber("admin").isEmpty()){
                HashSet<String> roles = new HashSet<>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .phoneNumber("admin")
                        .roles(roles)
                        .password(passwordEncoder().encode("admin"))
                        .build();
                userRepository.save(user);
                log.warn("Admin user have been created");
            }
        };
    }
}
