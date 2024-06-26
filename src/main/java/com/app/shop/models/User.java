package com.app.shop.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "users")
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "fullname", length = 100)
    String fullName;
    @Column(name = "phone_number", length = 10, nullable = false)
    String phoneNumber;
    @Column(name = "address", length = 200, nullable = false)
    String address;
    @Column(name = "password", length = 200, nullable = false)
    String password;
    @Column(name = "is_active")
    boolean active;
    @Column(name = "date_of_birth")
    Date dateOfBirth;
    @Column(name = "facebook_account_id")
    int facebookAccountId;
    @Column(name = "google_account_id")
    int googleAccountId;
    @ManyToMany
    Set<Role> roles;
}
