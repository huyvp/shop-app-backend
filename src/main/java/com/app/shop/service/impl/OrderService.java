package com.app.shop.service.impl;

import com.app.shop.dto.OrderDTO;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderMapper;
import com.app.shop.models.Order;
import com.app.shop.models.User;
import com.app.shop.repo.OrderRepository;
import com.app.shop.repo.UserRepository;
import com.app.shop.response.OrderResponse;
import com.app.shop.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    UserRepository userRepository;
    OrderMapper orderMapper;
    OrderRepository orderRepository;

    @Override
    public OrderResponse createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        Order order = orderMapper.toOrder(orderDTO);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setActive(true);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getAllOrder(PageRequest pageRequest) {
        return orderRepository.findAll(pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public Page<OrderResponse> getOrderByUserId(long userId, PageRequest pageRequest) {
        userRepository.findById(userId)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return orderRepository.findByUserId(userId, pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public void updateOrder(long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        orderMapper.updateOrder(orderDTO, order);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        orderRepository.delete(order);
    }
}
