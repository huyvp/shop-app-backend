package com.app.shop.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false,length = 350)
    private String name;
    private double price;
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @Column(name = "category_id")
    private Category categoryId;
}
