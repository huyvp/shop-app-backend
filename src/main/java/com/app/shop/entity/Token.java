package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token", nullable = false, length = 255)
    private String token;
    @Column(name = "token_type")
    private String tokenType;
    @Column(name = "expired_in")
    private LocalDateTime expiredIn;
    private boolean revoked;
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;
}
