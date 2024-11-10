package com.app.shop.service;

import com.app.shop.dto.request.order.OrderReq;
import com.app.shop.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;

public interface IOrderService {
    OrderResponse createOrder(OrderReq orderReq);

    OrderResponse getOrderById(long id);
    Page<OrderResponse> getMyOrder(PageRequest pageRequest);

    @PreAuthorize("hasAuthority('MODIFY_DATA')")
    Page<OrderResponse> getAllOrder(PageRequest pageRequest);

    void updateOrder(long id, OrderReq orderReq);

    void deleteOrder(long id);
}
