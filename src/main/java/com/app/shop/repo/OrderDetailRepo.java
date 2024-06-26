package com.app.shop.repo;

import com.app.shop.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long OrderId);
}
