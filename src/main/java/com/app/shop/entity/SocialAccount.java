package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "social_accounts")
@Entity
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "provider", nullable = false, length = 50)
    private String provider;
    @Column(name = "provider_id", length = 50)
    private String providerId;
    @Column(name = "name", length = 150)
    private String name;
    @Column(name = "email", length = 150)
    private String email;
}
