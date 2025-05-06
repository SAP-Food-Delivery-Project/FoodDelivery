package org.example.fooddelivery.controllers;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.CreateOrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDto;
import org.example.fooddelivery.entities.dtos.OrderDtos.OrderDtoWithId;
import org.example.fooddelivery.services.contracts.OrderService;
import org.example.fooddelivery.services.contracts.UserOperationService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class UserOperationController {

    private final UserOperationService userOperationService;
    private final OrderService orderService;

    @GetMapping
    public List<FoodProducerDto> getAllFoodProducer(){
        return userOperationService.getAllFoodProducers();
    }

    @GetMapping("/foods/{foodProducerId}/{foodType}")
    public List<FoodDto> getAllFilteredFoods
            (@PathVariable int foodProducerId, @PathVariable String foodType){
        return userOperationService.getAllFilteredFood(foodProducerId, foodType);
    }

    @GetMapping("/beverages/{foodProducerId}/{beverageType}")
    public List<BeverageDto> getAllFilteredBeverage
            (@PathVariable int foodProducerId, @PathVariable String beverageType){
        return userOperationService.getAllFilteredBeverages(foodProducerId, beverageType);
    }

    @PostMapping()
    public OrderDtoWithId createOrder(@RequestBody CreateOrderDto createOrderDto){
        return orderService.createOrder(createOrderDto);
    }

    @GetMapping("/order")
    public OrderDto getOrderInformation(int id){
        return orderService.getOrderById(id);
    }
}
