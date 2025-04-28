package org.example.fooddelivery.controllers;

import lombok.RequiredArgsConstructor;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.BeverageDtoWithId;
import org.example.fooddelivery.entities.dtos.BeverageDtos.CreateBeverageDto;
import org.example.fooddelivery.entities.dtos.BeverageDtos.UpdateBeverageDto;
import org.example.fooddelivery.entities.dtos.UserDtos.UpdateUserDto;
import org.example.fooddelivery.services.contracts.BeverageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/beverage")
public class BeverageController {

    private final BeverageService beverageService;

    @GetMapping("/{id}")
    public BeverageDto getBeverageById(@PathVariable int id){
        return beverageService.getSingleBeverageById(id);
    }

    @GetMapping("/name/{name}")
    public BeverageDto getBeverageByName(@PathVariable String name){
        return beverageService.getSingleBeverageByName(name);
    }

    @PostMapping
    public BeverageDtoWithId createBeverage(@RequestBody CreateBeverageDto createBeverageDto){
        return beverageService.createBeverage(createBeverageDto);
    }

    @PutMapping
    public BeverageDto updateBeverage
            (@PathVariable int id, @RequestBody UpdateBeverageDto updateBeverageDto){
        return beverageService.updateBeverage(updateBeverageDto, id);
    }

    @DeleteMapping
    public ResponseEntity<String> deactivateBeverage(int id){
        beverageService.deactivateBeverage(id);

        return ResponseEntity.ok("Beverage deactivated successfully!");

    }
}
