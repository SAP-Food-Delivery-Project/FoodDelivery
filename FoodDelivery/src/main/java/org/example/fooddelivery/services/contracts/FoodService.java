package org.example.fooddelivery.services.contracts;

import org.example.fooddelivery.entities.dtos.FoodDtos.CreateFoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDto;
import org.example.fooddelivery.entities.dtos.FoodDtos.FoodDtoWithId;
import org.example.fooddelivery.entities.dtos.FoodDtos.UpdateFoodDto;

public interface FoodService {

    FoodDto getSingleFood(int id);

    FoodDto getSingleFood(String name);

    FoodDtoWithId createFood(CreateFoodDto createFoodDto);

    FoodDto updateFood(UpdateFoodDto updateFoodDto, int id);

    void deactivateFood(int id);
}
