package com.app.shop.service;

import com.app.shop.dto.order.OrderDTO;
import com.app.shop.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO);

    OrderResponse getOrderById(long id);

    Page<OrderResponse> getOrderByUserId(long userId, PageRequest pageRequest);

    @PreAuthorize("hasAuthority('MODIFY_DATA')")
    Page<OrderResponse> getAllOrder(PageRequest pageRequest);

    void updateOrder(long id, OrderDTO orderDTO);

    void deleteOrder(long id);
}
