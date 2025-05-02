package org.example.fooddelivery.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.CreateOrderBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDto;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.OrderedBeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.OrderedProductsBeverages.UpdateOrderBeverageDto;
import org.example.fooddelivery.services.contracts.OrderProductsBeveragesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders/beverages")
public class OrderProductBeverageController {

    private final OrderProductsBeveragesService orderProductsBeveragesService;

    @GetMapping("/{id}")
    public OrderedBeverageDto getOrderedBeverageDtoById(@PathVariable int id) {
        return orderProductsBeveragesService.getOrderProductBeverageById(id);
    }

    @GetMapping
    public List<OrderedBeverageDto> getAllOrderedBeverages() {
        return orderProductsBeveragesService.getAllOrderedBeverages();
    }

    @PostMapping
    public OrderedBeverageDtoWithId createOrderedBeverage(@RequestBody CreateOrderBeverageDto createOrderBeverageDto) {
        return orderProductsBeveragesService.createOrderBeverage(createOrderBeverageDto);
    }


    @PutMapping("/{id}")
    public OrderedBeverageDto updateOrderedBeverage(@PathVariable int id, @RequestBody UpdateOrderBeverageDto updateOrderBeverageDto) {
        return orderProductsBeveragesService.updateOrderBeverage(updateOrderBeverageDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateOrderBeverage(@Valid @PathVariable int id){
        orderProductsBeveragesService.deactivateOrderedBeverage(id);
        return ResponseEntity.ok("Ordered beverage deactivated successfully!");
    }

}
