package com.app.shop.service;

import com.app.shop.dto.OrderDTO;
import com.app.shop.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO orderDTO);

    OrderResponse getOrderById(long id);

    Page<OrderResponse> getOrderByUserId(long userId, PageRequest pageRequest);

    Page<OrderResponse> getAllOrder(PageRequest pageRequest);

    void updateOrder(long id, OrderDTO orderDTO);

    void deleteOrder(long id);
}
