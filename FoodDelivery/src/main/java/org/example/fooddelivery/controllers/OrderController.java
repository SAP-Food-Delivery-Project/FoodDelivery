package org.example.fooddelivery.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.OrderDtos.CreateOrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderDtos.UpdateOrderDto;
import org.example.fooddelivery.services.contracts.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public OrderDtoWithId createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return orderService.createOrder(createOrderDto);
    }


    @PutMapping("/{id}")
    public OrderDto updateOrder(@PathVariable int id, @RequestBody UpdateOrderDto updateOrderDto) {
        return orderService.updateOrder(updateOrderDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateOrder(@Valid @PathVariable int id){
        orderService.deactivateOrder(id);
        return ResponseEntity.ok("Order deactivated successfully!");
    }

}
