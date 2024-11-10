package com.app.shop.service;

import com.app.shop.dto.request.orderDetail.OrderDetailReq;
import com.app.shop.dto.response.OrderDetailResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailResponse create(OrderDetailReq orderDetailReq);

    OrderDetailResponse update(long id, OrderDetailReq orderDetailReq);

    OrderDetailResponse getById(long id);

    List<OrderDetailResponse> getByOrderId(long orderId);

    void delete(long id);

}
