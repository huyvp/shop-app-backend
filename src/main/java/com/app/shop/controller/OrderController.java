package com.app.shop.controller;

import com.app.shop.dto.order.OrderDTO;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.response.OrderResponse;
import com.app.shop.service.IOrderService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    IOrderService orderService;

    @PostMapping()
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return ResponseHandler.execute(
                orderService.createOrder(orderDTO)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateOrder(@Valid @PathVariable int id,
                                         @Valid @RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(id, orderDTO);
        return ResponseHandler.execute(null);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrder(@Valid @PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseHandler.execute(null);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<Object> getOrderByUserId(
            @Valid @PathVariable("user_id") Long userId,
            @RequestParam("limit") int limit,
            @RequestParam("page") int page) {
        Page<OrderResponse> responsePage = orderService.getOrderByUserId(
                userId,
                PageRequest.of(page, limit, Sort.by("order_date").ascending())
        );
        int totalPages = responsePage.getTotalPages();
        List<OrderResponse> orders = responsePage.getContent();
        return ResponseHandler.execute(orders, totalPages);
    }

    @GetMapping()
    public ResponseEntity<Object> getOrders(
            @RequestParam("limit") int limit,
            @RequestParam("page") int page) {
        Page<OrderResponse> responsePage = orderService.getAllOrder(
                PageRequest.of(page, limit, Sort.by("id").ascending())
        );
        int totalPages = responsePage.getTotalPages();
        List<OrderResponse> orders = responsePage.getContent();
        return ResponseHandler.execute(orders, totalPages);
    }
}
