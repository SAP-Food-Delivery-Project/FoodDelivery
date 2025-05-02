package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.CreateOrderBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.UpdateOrderBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.CreateOrderFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.OrderedFoodDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsFoods.UpdateOrderFoodDto;
import org.example.fooddelivery.services.contracts.OrderProductsBeveragesService;
import org.example.fooddelivery.services.contracts.OrderProductsFoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders/foods")
public class OrderProductFoodController {

    private final OrderProductsFoodsService orderProductsFoodsService;

    @GetMapping("/{id}")
    public OrderedFoodDto getOrderedFoodDtoById(@PathVariable int id) {
        return orderProductsFoodsService.getOrderProductFoodById(id);
    }

    @GetMapping
    public List<OrderedFoodDto> getAllOrderedFoods() {
        return orderProductsFoodsService.getAllOrderedfoods();
    }

    @PostMapping
    public OrderedFoodDtoWithId createOrderedFood(@RequestBody CreateOrderFoodDto createOrderFoodDto) {
        return orderProductsFoodsService.createOrderFood(createOrderFoodDto);
    }


    @PutMapping("/{id}")
    public OrderedFoodDto updateOrderedFood(@PathVariable int id, @RequestBody UpdateOrderFoodDto updateOrderFoodDto) {
        return orderProductsFoodsService.updateOrderedFood(updateOrderFoodDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateOrderFood(@Valid @PathVariable int id){
        orderProductsFoodsService.deactivateOrderedFood(id);
        return ResponseEntity.ok("Ordered food deactivated successfully!");
    }

}
