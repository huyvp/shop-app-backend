package com.app.shop.service.impl;

import com.app.shop.dto.OrderDTO;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderMapper;
import com.app.shop.models.Category;
import com.app.shop.models.Order;
import com.app.shop.repo.OrderRepository;
import com.app.shop.repo.UserRepository;
import com.app.shop.response.OrderResponse;
import com.app.shop.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    UserRepository userRepository;
    OrderMapper orderMapper;
    OrderRepository orderRepository;
    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {
        userRepository.findById(orderDTO.getUserId())
                .orElseThrow(()-> new ShopAppException(ErrorCode.USER_3002));
        Order order = orderMapper.toOrder(orderDTO);
        order.setOrderDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(long id) {
        return null;
    }

    @Override
    public List<OrderResponse> getALlOrders() {
        return null;
    }

    @Override
    public void updateOrder(long id, OrderDTO OrderDTO) {

    }

    @Override
    public void deleteOrder(long id) {

    }
}
