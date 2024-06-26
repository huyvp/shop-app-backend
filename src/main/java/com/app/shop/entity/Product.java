package com.app.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 350)
    private String name;
    private Float price;
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;
    @Column(name = "description")
    private String description;
    // FetchType.LAZY: Thuộc tính category của product chỉ truy vấn CSDL khi cần chứ không vào lúc product lấy ra từ CSDL
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
