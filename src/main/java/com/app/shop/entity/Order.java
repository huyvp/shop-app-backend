package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Column(name = "fullname", length = 100)
    String fullName;
    @Column(name = "email", length = 100)
    String email;
    @Column(name = "phone_number", length = 100, nullable = false)
    String phoneNumber;
    @Column(name = "address", length = 100)
    String address;
    @Column(name = "note", length = 100)
    String note;
    @Column(name = "order_date")
    LocalDateTime orderDate;
    @Column(name = "status")
    String status;
    @Column(name = "total_money")
    Float totalMoney;
    @Column(name = "shipping_method")
    String shippingMethod;
    @Column(name = "shipping_address")
    String shippingAddress;
    @Column(name = "shipping_date")
    Date shippingDate;
    @Column(name = "tracking_number")
    String trackingNumber;
    @Column(name = "payment_method")
    String paymentMethod;
    @Column(name = "active")
    Boolean active;
}
