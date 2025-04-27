package org.example.fooddelivery.controllers;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.FoodProducer;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.CreateFoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDto;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.FoodProducerDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodProductDtos.UpdateFoodProducerDto;
import org.example.fooddelivery.services.contracts.FoodProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/foodproducers")
public class FoodProducerController {

    private final FoodProducerService foodProducerService;

    @GetMapping("/{id}")
    public FoodProducerDto getFoodProducerById(@PathVariable int id){
        return this.foodProducerService.getSingleFoodProducer(id);
    }

    @GetMapping("/name/{name}")
    public FoodProducerDto getFoodProducerByName(@PathVariable String name){
        return this.foodProducerService.getSingleFoodProducer(name);
    }

    @GetMapping("/city/{city}/address/{address}")
    public FoodProducerDto getFoodProdycerByAddressAndCity(@PathVariable String city, String address){
        return this.foodProducerService.getSingleFoodProducer(city, address);
    }

    @PostMapping
    public FoodProducerDtoWithId createFoodProducer(@RequestBody CreateFoodProducerDto createFoodProducerDto){
        return this.foodProducerService.createFoodProducer(createFoodProducerDto);
    }

    @PutMapping("/{id}")
    public FoodProducerDto updateFoodProducer(@PathVariable int id, @RequestBody
                                              UpdateFoodProducerDto updateFoodProducerDto){

        return this.foodProducerService.updateFoodProducer(updateFoodProducerDto, id);
    }

    @DeleteMapping("/{id}")
    public void deactivateFoodProducer(@PathVariable int id){
        this.foodProducerService.deactivateFoodProducer(id);
    }
}
