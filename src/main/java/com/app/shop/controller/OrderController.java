package com.app.shop.controller;

import com.app.shop.dto.request.order.OrderReq;
import com.app.shop.handler.ResponseHandler;
import com.app.shop.dto.response.OrderResponse;
import com.app.shop.service.IOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "order")
public class OrderController {
    IOrderService orderService;

    @PostMapping()
    public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderReq orderReq) {
        return ResponseHandler.execute(
                orderService.createOrder(orderReq)
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateOrder(@Valid @PathVariable int id,
                                              @Valid @RequestBody OrderReq orderReq) {
        orderService.updateOrder(id, orderReq);
        return ResponseHandler.execute();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteOrder(@Valid @PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseHandler.execute();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getOrderById(@Valid @PathVariable("id") Long id) {
        return ResponseHandler.execute(
                orderService.getOrderById(id)
        );
    }

    @GetMapping(value = "/my_orders")
    public ResponseEntity<Object> getMyOrders(
            @RequestParam("limit") int limit,
            @RequestParam("page") int page) {
        Page<OrderResponse> responsePage = orderService.getMyOrder(
                PageRequest.of(page, limit, Sort.by("orderDate").ascending())
        );
        int totalPages = responsePage.getTotalPages();
        List<OrderResponse> orders = responsePage.getContent();
        return ResponseHandler.execute(orders, totalPages);
    }

    @GetMapping
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
