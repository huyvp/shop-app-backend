package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_details")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "price", nullable = false)
    private Float price;
    @Column(name = "number_of_product",nullable = false)
    private int numberOfProduct;
    @Column(name = "total_money",nullable = false)
    private int totalMoney;
    @Column(name = "color")
    private String color;
}
