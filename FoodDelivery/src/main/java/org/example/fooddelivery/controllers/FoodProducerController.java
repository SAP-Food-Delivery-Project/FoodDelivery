package org.example.fooddelivery.controllers;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.CreateFoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.UpdateFoodProducerDto;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foodproducers")
public class FoodProducerController {

    private final FoodProducerService foodProducerService;

    @GetMapping("/{id}")
    public FoodProducerDto getFoodProducerById(@PathVariable int id){
        return foodProducerService.getSingleFoodProducer(id);
    }

    @GetMapping("/name/{name}")
    public FoodProducerDto getFoodProducerByName(@PathVariable String name){
        return foodProducerService.getSingleFoodProducer(name);
    }

    @GetMapping("/city/{city}/address/{address}")
    public FoodProducerDto getFoodProducerByAddressAndCity(@PathVariable String city, String address){
        return foodProducerService.getSingleFoodProducer(city, address);
    }

    @PostMapping
    public FoodProducerDtoWithId createFoodProducer(@RequestBody CreateFoodProducerDto createFoodProducerDto){
        return foodProducerService.createFoodProducer(createFoodProducerDto);
    }

    @PutMapping("/{id}")
    public FoodProducerDto updateFoodProducer(@PathVariable int id, @RequestBody
                                              UpdateFoodProducerDto updateFoodProducerDto){

        return foodProducerService.updateFoodProducer(updateFoodProducerDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateFoodProducer(@PathVariable int id){
            foodProducerService.deactivateFoodProducer(id);

        return ResponseEntity.ok("Food producer deactivated successfully!");
    }
}
