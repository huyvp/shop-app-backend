package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "roles")
@Entity
public class Role {
    @Id
    String name;
    String description;
    @ManyToMany
    Set<Permission> permissions;
}
