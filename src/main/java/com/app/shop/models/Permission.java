package com.app.shop.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "permissions")
@Entity
public class Permission {
    @Id
    String name;
    String description;
}

