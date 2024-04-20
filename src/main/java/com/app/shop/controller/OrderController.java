package com.app.shop.controller;

import com.app.shop.dto.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping()
    public String createOrder(@Valid @RequestBody OrderDTO orderDTO){
        return "Create order";
    }
    @GetMapping("/{user_id}")
    public String getOrders(
            @Valid @PathVariable("user_id") Long userId,
            @RequestParam("limit") int limit,
            @RequestParam("page") int page){
        return "Get orders of a user";
    }
    @PutMapping("{id}")
    public String updateOrder(@Valid @PathVariable int id, @Valid @RequestBody OrderDTO orderDTO){
        return "Update order";
    }
    @DeleteMapping("{id}")
    public String deleteOrder(@Valid @PathVariable int id){
        return "Delete order";
    }
}
