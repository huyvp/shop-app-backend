package com.app.shop.service.impl;

import com.app.shop.dto.request.order.OrderReq;
import com.app.shop.entity.Order;
import com.app.shop.entity.User;
import com.app.shop.exception.ErrorCode;
import com.app.shop.exception.ShopAppException;
import com.app.shop.mapper.OrderMapper;
import com.app.shop.repo.OrderRepo;
import com.app.shop.repo.UserRepo;
import com.app.shop.dto.response.OrderResponse;
import com.app.shop.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.app.shop.constant.Constants.ORDER_STATUS.PENDING;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    UserRepo userRepo;
    OrderMapper orderMapper;
    OrderRepo orderRepo;

    @Override
    public OrderResponse createOrder(OrderReq orderReq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findById(orderReq.getUserId())
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        if (!user.getPhoneNumber().equals(authentication.getName()))
            throw new ShopAppException(ErrorCode.AUTH_4000);
        Order order = orderMapper.toOrder(orderReq);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setActive(true);
        order.setStatus(PENDING);
        Order savedOrder = orderRepo.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    @Override
    public OrderResponse getOrderById(long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public Page<OrderResponse> getAllOrder(PageRequest pageRequest) {
        return orderRepo.findAll(pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public Page<OrderResponse> getMyOrder(PageRequest pageRequest) {
        String userPhone = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByPhoneNumber(userPhone)
                .orElseThrow(() -> new ShopAppException(ErrorCode.USER_3002));
        return orderRepo.findByUser(user, pageRequest)
                .map(orderMapper::toOrderResponse);
    }

    @Override
    public void updateOrder(long id, OrderReq orderReq) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        orderMapper.updateOrder(orderReq, order);
        orderRepo.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ShopAppException(ErrorCode.ORDER_3002));
        order.setActive(false);
        orderRepo.delete(order);
    }
}
