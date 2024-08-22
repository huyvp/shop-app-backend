package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_details")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
    @Column(name = "price", nullable = false)
    float price;
    @Column(name = "number_of_product",nullable = false)
    int numberOfProduct;
    @Column(name = "total_money",nullable = false)
    int totalMoney;
    @Column(name = "color")
    String color;
}
