package com.app.shop.repo;

import com.app.shop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(Long userId, Pageable pageable);
}
