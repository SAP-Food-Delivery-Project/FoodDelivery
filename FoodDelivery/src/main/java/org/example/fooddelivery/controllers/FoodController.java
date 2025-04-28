package org.example.fooddelivery.controllers;


import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.FoodDtos.CreateFoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodDtos.UpdateFoodDto;
import org.example.fooddelivery.services.contracts.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/{id}")
    public FoodDto getFoodById(@PathVariable int id){
        return foodService.getSingleFood(id);
    }

    @GetMapping("/name/{name}")
    public FoodDto getFoodByName(@PathVariable String name){
        return foodService.getSingleFood(name);
    }

    @PostMapping
    public FoodDtoWithId createFood(@RequestBody CreateFoodDto createFoodDto){
        return foodService.createFood(createFoodDto);
    }

    @PutMapping("/{id}")
    public FoodDto updateFood(@RequestBody UpdateFoodDto updateFoodDto, int id){
        return foodService.updateFood(updateFoodDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deactivateFood(@PathVariable int id){
        foodService.deactivateFood(id);

        return ResponseEntity.ok("Food deactivated successfully!");
    }

}
