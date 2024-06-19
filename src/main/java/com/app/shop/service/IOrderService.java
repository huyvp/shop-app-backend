package com.app.shop.service;

import com.app.shop.dto.OrderDTO;
import com.app.shop.response.OrderResponse;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderDTO OrderDTO);

    OrderResponse getOrderById(long id);

    List<OrderResponse> getALlOrders();

    void updateOrder(long id, OrderDTO OrderDTO);

    void deleteOrder(long id);
}
